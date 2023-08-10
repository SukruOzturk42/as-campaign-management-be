package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reward_gift_ticket_type")
public class RewardGiftTicketTypeEntity extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    public RewardGiftTicketType toModel() {
        return RewardGiftTicketType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }
}
