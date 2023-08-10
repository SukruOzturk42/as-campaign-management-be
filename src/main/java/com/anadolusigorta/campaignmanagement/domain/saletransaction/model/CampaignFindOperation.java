package com.anadolusigorta.campaignmanagement.domain.saletransaction.model;



import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request.CustomerCampaignsRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFindOperation {
   private  String transactionId;
   private String contactNumber;
   private List<ContactCampaignInformation> contactCampaignInformationList;

   private CustomerCampaignsRequest request;


}
