package com.anadolusigorta.campaignmanagement.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {



    private String name;

    private String description;

    private Long code;

    private String auth;

}
