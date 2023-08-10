package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.NotifySaleCampaignRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifySaleCampaignResponse {

	private String contactNumber;

	private String transactionId;

	private String result;

	private Boolean success;

	private String extraInformation;

	private List<NotifySaleCampaignResponse> insuredNotifySaleCampaignResponse;

	public static NotifySaleCampaignResponse fromModel(SaleInformation createNotifySaleCampaign) {
		log.info(String.format("Handle Notify Service Response Sale Information: %s",
				createNotifySaleCampaign.toString()));
		return NotifySaleCampaignResponse.builder()
				.contactNumber(createNotifySaleCampaign.getContactNumber())
				.transactionId(createNotifySaleCampaign.getTransactionId())
				.insuredNotifySaleCampaignResponse(createNotifySaleCampaign.getInsuredSaleInformation()!=null?
						fromListModel(createNotifySaleCampaign.getInsuredSaleInformation()):null)
				.success(Boolean.TRUE).build();
	}

	public static List<NotifySaleCampaignResponse> fromListModel(List<SaleInformation> saleInformationList){
		return saleInformationList.stream()
				.map(NotifySaleCampaignResponse::fromModel)
				.collect(Collectors.toList());
	}
}
