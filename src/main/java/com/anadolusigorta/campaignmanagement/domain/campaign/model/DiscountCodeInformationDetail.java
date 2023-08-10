package com.anadolusigorta.campaignmanagement.domain.campaign.model;

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
public class DiscountCodeInformationDetail implements Serializable {

    private Long id;

    private String updater;

    private String description;

    private LocalDateTime createdAt;

}
