package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleGiftCodeInformationDetail implements Serializable {

    private Long id;

    private String updater;

    private String description;

    private LocalDateTime createdAt;

    private Long codeCount;
}
