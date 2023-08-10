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
public class CodeCriteria implements Serializable {

    private String code;

    private String contactNumber;
}
