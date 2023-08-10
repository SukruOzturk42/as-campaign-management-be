package com.anadolusigorta.campaignmanagement.domain.menuitem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubMenuItem {

    private Long id;

    private String title;

    private String name;
    
    private String menuRoute;

    private String auth;

    private Integer order;



}
