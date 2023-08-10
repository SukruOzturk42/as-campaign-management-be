package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "gift_delivery_start_type_group")
public class GiftDeliveryStartTypeGroup extends AbstractEntity {

    private Long campaignTypeId;

    @OneToOne
    @JoinColumn(name = "gift_delivery_start_type_id")
    private GiftDeliveryStartTypeEntity giftDeliveryStartType;
}
