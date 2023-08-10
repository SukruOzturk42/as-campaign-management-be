package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateDiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.DiscountCodeInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformationCriteria;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DiscountCodeInformationFacade {

    private final DiscountCodeInformationRepository discountCodeInformationRepository;
    private final UserSecurityFacade userSecurityFacade;

    public List<DiscountCode> getAllDiscountCodeByDiscountCodeInformation(Long discountInformationId) {
        return discountCodeInformationRepository.getAllDiscountCodeByDiscountCodeInformation(discountInformationId);
    }

    public PageContent<DiscountCodeInformation> getAllDiscountCodeInformation(DiscountCodeInformationCriteria discountCodeInformationCriteria, Pageable pageable) {
        return discountCodeInformationRepository.getDiscountCodeInformationList(discountCodeInformationCriteria, pageable);
    }

    public PageContent<DiscountCodeInformation> getAllDiscountCodeInformation(String codeGroupName, String contactNumber, Pageable pageable) {
        return discountCodeInformationRepository.getDiscountCodeInformationList(codeGroupName, contactNumber, pageable);
    }

    @Async
    public DiscountCodeInformation saveDiscountCodeInformation(CreateDiscountCodeInformation campaignCodeInformation) {

        var discountCodeInformation = discountCodeInformationRepository.saveDiscountCodeInformation(campaignCodeInformation);
        var userFullName = userSecurityFacade.getActiveUser().getFullName();

        CompletableFuture.supplyAsync(() -> {
            discountCodeInformationRepository.saveDiscountCodes(campaignCodeInformation, discountCodeInformation);
            discountCodeInformationRepository.createDiscountCodeInformationDetail(campaignCodeInformation, discountCodeInformation, userFullName);

            return "discount codes uploaded";
        });
        return discountCodeInformation;
    }

    public List<DiscountCodeInformation> getAllActiveDiscountCodeInformation() {
        return discountCodeInformationRepository.getAllActiveDiscountCodeInformation();
    }

    public List<DiscountCode> getAllDiscountCodes() {
        return discountCodeInformationRepository.getAllDiscountCodes();
    }

    public PageContent<DiscountCode> getAllDiscountCodes(String codeGroupName, String contactNumber,
                                                         String code, String codeStatus, Pageable pageable) {
        return discountCodeInformationRepository.getAllDiscountCodes(codeGroupName, contactNumber, code, codeStatus, pageable);
    }

    public List<UsedCodeGroupInformation> getCampaignsByUsedDiscountCodeInformationId(Long discountCodeInformationId) {
        return discountCodeInformationRepository.getCampaignListByUsedDiscountCodeInformationId(discountCodeInformationId);
    }


}
