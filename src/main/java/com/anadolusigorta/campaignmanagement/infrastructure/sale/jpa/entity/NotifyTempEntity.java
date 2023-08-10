package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.SaleCodeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.google.gson.Gson;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notify_temp_5")
public class NotifyTempEntity {


    @Id
    @Column(name = "id")
    private Long id;
    private String request;


}
