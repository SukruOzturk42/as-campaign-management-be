package com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.recordtracking.model.SaleProcess;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleProcessResponse {

    private Long id;

    private String name;

    private String description;

    private String transactionId;

    private String request;

    private String contactNumber;

    private LocalDateTime createdAt;

    public static com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto.SaleProcessResponse fromModel(SaleProcess saleProcess) {
        return com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto.SaleProcessResponse.builder()
                .id(saleProcess.getId())
                .name(saleProcess.getName())
                .description(saleProcess.getDescription())
                .build();
    }

    public static SaleProcessResponse fromModel(SaleOperationsRequests findSaleCampaignsRequest) {
        return SaleProcessResponse.builder()
                .id(findSaleCampaignsRequest.getId())
                .transactionId(findSaleCampaignsRequest.getTransactionId())
                .request(findSaleCampaignsRequest.getRequest())
                .contactNumber(findSaleCampaignsRequest.getContactNumber())
                .createdAt(findSaleCampaignsRequest.getCreatedAt())
                .build();
    }



    public static List<com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto.SaleProcessResponse> fromListOfModel(List<SaleProcess> saleProcessList) {
        return saleProcessList.stream().map(com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto.SaleProcessResponse::fromModel).collect(Collectors.toList());
    }


     public static PageContent<SaleProcessResponse> fromPageOfModel(PageContent<SaleOperationsRequests> findSaleCampaignsRequests) {
        return PageContent.<SaleProcessResponse>builder()
                .content(findSaleCampaignsRequests.getContent().stream().map(SaleProcessResponse::fromModel)
                        .collect(Collectors.toList()))
                .page(findSaleCampaignsRequests.getPage())
                .size(findSaleCampaignsRequests.getSize())
                .totalItems(findSaleCampaignsRequests.getTotalItems())
                .build();
    }
    }
