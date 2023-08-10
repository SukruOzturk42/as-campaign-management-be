package com.anadolusigorta.campaignmanagement.domain.contact.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactGroupCriteria {

    private String groupName;
}
