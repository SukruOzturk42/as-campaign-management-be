package com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.recordtracking.model.SaleProcessCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleProcessCriteriaRequest {

    private String contactNo;

    private String transactionId;

    public SaleProcessCriteria toModel(){
        return  SaleProcessCriteria.builder()
                .contactNo(contactNo)
                .transactionId(transactionId)
                .build();
    }
}
