package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleResponse {

    private List<PolicySaleCustomer> codes;
}
