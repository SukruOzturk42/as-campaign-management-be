/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.company.model */

package com.anadolusigorta.campaignmanagement.domain.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCampaignStructure {

	private Long id;

	private Long companyId;

	private String name;

	private String description;

	private String rewardName;

	private String rewardDescription;

	private List<CompanyCampaignStructure> children=new ArrayList<>();
}
