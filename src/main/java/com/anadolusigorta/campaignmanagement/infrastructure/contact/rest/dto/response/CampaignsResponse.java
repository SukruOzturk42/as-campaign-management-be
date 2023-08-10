/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignsResponse {
	private List<CampaignInformationResponse> campaigns;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CampaignInformationResponse {

		private Long campaignId;

		private String campaignName;

		private String shortDescription;

		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		private LocalDateTime campaignStartDate;

		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		private LocalDateTime campaignEndDate;

		private Long campaignVersion;

		private String campaignType;

		private String campaignTitle;

	}
	public static CampaignInformationResponse fromModel(CampaignInformation campaignInformation) {
		return CampaignInformationResponse.builder()
				.campaignVersion(campaignInformation.getVersion())
				.campaignId(campaignInformation.getCampaignId())
				.campaignName(campaignInformation.getCampaignName())
				.campaignStartDate(campaignInformation.getCampaignStartDate())
				.campaignEndDate(campaignInformation.getCampaignEndDate())
				.shortDescription(campaignInformation.getShortDescription())
				.campaignType(campaignInformation.getCampaignType() != null ?
						campaignInformation.getCampaignType().getName()
						: null)
				.campaignTitle(campaignInformation.getCampaignTitle())
				.build();
	}

	public static List<CampaignInformationResponse> fromListOfModel(List<CampaignInformation> campaigns) {
		return campaigns.stream().map(CampaignsResponse::fromModel).collect(Collectors.toList());
	}

}
