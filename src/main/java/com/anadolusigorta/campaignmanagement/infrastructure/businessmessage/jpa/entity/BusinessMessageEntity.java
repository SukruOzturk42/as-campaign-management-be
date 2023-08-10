package com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.entity;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.request.CreateBusinessMessageRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_message")
public class BusinessMessageEntity extends AbstractEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name="key",nullable = false, unique = true, length = 400)
    private String key;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @Column(name="note",length = 400)
    private String note;

    public static BusinessMessageEntity fromModel(CreateBusinessMessageRequest createBusinessMessageRequest){

        return BusinessMessageEntity.builder()
                .code(createBusinessMessageRequest.getCode())
                .key(createBusinessMessageRequest.getKey())
                .description(createBusinessMessageRequest.getDescription())
                .note(createBusinessMessageRequest.getNote())
                .build();
    }

}
