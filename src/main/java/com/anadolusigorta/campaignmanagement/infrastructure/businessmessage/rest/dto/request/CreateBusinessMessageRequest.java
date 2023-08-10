package com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBusinessMessageRequest {

    private Long id;

    private String code;

    private String key;

    private String description;

    private String note;

}
