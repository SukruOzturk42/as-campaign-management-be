package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.CampaignFindOperation;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.util.GlobalGson;
import com.google.gson.Gson;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_find_campaigns_request")
public class FindSaleCampaignsRequestEntity extends AbstractEntity {

    private String transactionId;

    @Column(length = 5000)
    private String request;

    private String contactNumber;

    private LocalDateTime createdAt;


    public static FindSaleCampaignsRequestEntity fromModel(CampaignFindOperation campaignFindOperation){
        return FindSaleCampaignsRequestEntity.builder()
                .transactionId(campaignFindOperation.getTransactionId())
                .request(GlobalGson.get().toJson(campaignFindOperation.getRequest()))
                .contactNumber(campaignFindOperation.getContactNumber())
                .build();
    }

    public SaleOperationsRequests toModel() {
        return SaleOperationsRequests.builder()
                .id(super.getId())
                .transactionId(transactionId)
                .request(request)
                .contactNumber(contactNumber)
                .createdAt(getCreatedAt())
                .build();
    }

}
