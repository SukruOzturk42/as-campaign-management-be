package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeResponse {

    private String fetchingCampaigns;
    private String fetchingMatValues;

}
