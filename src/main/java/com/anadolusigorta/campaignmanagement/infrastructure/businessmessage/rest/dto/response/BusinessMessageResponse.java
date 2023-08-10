package com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.response;

import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.entity.BusinessMessageEntity;
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
public class BusinessMessageResponse {

    private Long id;

    private String code;

    private String key;

    private String description;

    private String note;

    private LocalDateTime createdAt;

    public static BusinessMessageResponse fromModel(BusinessMessageEntity businessMessageEntity) {
        return BusinessMessageResponse.builder()
                .id(businessMessageEntity.getId())
                .code(businessMessageEntity.getCode())
                .key(businessMessageEntity.getKey())
                .description(businessMessageEntity.getDescription())
                .note(businessMessageEntity.getNote())
                .createdAt(businessMessageEntity.getCreatedAt())
                .build();
    }

    public static List<BusinessMessageResponse> fromListOfModel(List<BusinessMessageEntity> businessMessageEntityList) {
        return businessMessageEntityList.stream().map(BusinessMessageResponse::fromModel).collect(Collectors.toList());
    }
}
