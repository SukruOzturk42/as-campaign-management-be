package com.anadolusigorta.campaignmanagement.domain.sale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleOperationsRequests {

    private Long id;

    private String transactionId;

    private String request;

    private String contactNumber;

    private LocalDateTime createdAt;
}
