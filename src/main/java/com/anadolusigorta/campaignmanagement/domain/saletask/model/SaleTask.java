/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.product */

package com.anadolusigorta.campaignmanagement.domain.saletask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleTask {

	private Agency agency;
    private TaskStatus status;
    private String taskOwner;
    private String description;
    private TaskState taskState;
    private SaleTaskGroup saleTaskGroup;
}
