package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftSendMethodType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "gift_send_method_type")
public class RewardGiftSendMethodTypeEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    public RewardGiftSendMethodType toModel(){
        return RewardGiftSendMethodType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }
}
