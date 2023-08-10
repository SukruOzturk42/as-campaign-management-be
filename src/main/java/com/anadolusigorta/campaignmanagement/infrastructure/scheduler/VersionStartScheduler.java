package com.anadolusigorta.campaignmanagement.infrastructure.scheduler;

import com.anadolusigorta.campaignmanagement.domain.cache.facade.CustomerCampaignRedisCacheFacade;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignInformationJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CustomerCampaignJpaRepository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.repository.CampaignStatusJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class VersionStartScheduler {



    private final CampaignInformationJpaRepository campaignInformationJpaRepository;
    private final CustomerCampaignJpaRepository customerCampaignJpaRepository;
    private final CampaignStatusJpaRepository campaignStatusJpaRepository;
    private final CustomerCampaignRedisCacheFacade customerCampaignRedisCacheFacade;

    //@Scheduled(cron = "0 */30 * ? * *")
    public void changeVersion() {

        if (false) {
            var campaignStatusPendingVersion = campaignStatusJpaRepository
                    .findByCampaignStatus(CampaignStatusEnum.PENDING_VERSION_START_DATE)
                    .orElseThrow(() -> new CampaignManagementException("campaign.status.not.found"));
            var campaignStatusActive = campaignStatusJpaRepository
                    .findByCampaignStatus(CampaignStatusEnum.ACTIVE_CAMPAIGN)
                    .orElseThrow(() -> new CampaignManagementException("campaign.status.not.found"));
            var campaignInformation = campaignInformationJpaRepository
                    .findByCampaignStatusAndVersionStartDateBefore(campaignStatusPendingVersion, LocalDateTime.now());

            campaignInformation.forEach(campaignInfo -> {
                var optionalCustomerCampaign = customerCampaignJpaRepository
                        .findByCampaignId(campaignInfo.getCampaign().getId());
                if (optionalCustomerCampaign.isPresent()) {
                    var customerCampaign = optionalCustomerCampaign.get();
                    campaignInfo.setCampaignStatus(campaignStatusActive);
                    customerCampaign.setCampaignVersion(campaignInfo.getCampaignVersion());
                    campaignInformationJpaRepository.save(campaignInfo);
                    customerCampaignJpaRepository.save(customerCampaign);
                }

            });
            customerCampaignRedisCacheFacade.reloadCustomerCampaignCache();

        }
    }
}
