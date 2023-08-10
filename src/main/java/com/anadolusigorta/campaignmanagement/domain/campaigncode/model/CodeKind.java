package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeKind implements Serializable {

    private Long id;

    private String name;

    private String description;

}
