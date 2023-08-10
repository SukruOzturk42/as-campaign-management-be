/* odeon_sukruo created on 23.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class CampaignInformation implements Serializable {

	private Long id;

	private Long campaignId;

	private Long version;

	private CampaignStatus campaignStatus;

	private CampaignApprovalStatus campaignApprovalStatus;

	private Long actionId;

	private CampaignGroup campaignGroup;

	private String campaignName;

	private String campaignTitle;

	private CampaignType campaignType;

	private List<String> tags;

	private LocalDateTime campaignStartDate;

	private LocalDateTime campaignEndDate;

	private LocalDateTime versionStartDate;

	private LocalDateTime createDate;

	private String campaignOwner;

	private String actionDescription;

	private String shortDescription;

	private Boolean hasCustomerLimit;

	private Integer customerLimitSize;

	private Boolean isTriggeredRewardSend;

	private Boolean isStartedRewardSend;

	public Boolean isActiveCampaign(){
		var localDateNow=LocalDateTime.now();
		log.info("localTime="+localDateNow.toString());
		log.info(String.format("campaignId:%s campaignStartDate:%s campaignEndDate:%s campaignStatus:%s",
				campaignId,campaignStartDate.toString(),campaignEndDate.toString(),campaignStatus.getStatus().getValue()));
		return campaignStartDate.isBefore(LocalDateTime.now())
				&& campaignEndDate.isAfter(LocalDateTime.now())
				&& (versionStartDate == null || versionStartDate.isBefore(LocalDateTime.now()))
				&& campaignStatus.getStatus().equals(CampaignStatusEnum.ACTIVE_CAMPAIGN);
	}

}
