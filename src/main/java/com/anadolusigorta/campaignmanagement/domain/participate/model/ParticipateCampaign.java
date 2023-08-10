/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.product */

package com.anadolusigorta.campaignmanagement.domain.participate.model;

import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipateCampaign {
	private String contactNumber;
	private String transactionId;
	private LocalDateTime updateDate;
	private Long campaignId;
	private Long ruleGroupId;
	private Long campaignVersion;
	private ContactCampaignInformation parameters;

	@Setter
	@Getter
	public static class ProductRequest {
		private Map<String,Object> productInfo;
		private List<Map<String,String>> subProducts;
		private List<Map<String,String>> ownerProducts;
	}

}
