package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroupCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactGroupCriteriaRequest {

    private String groupName;

    public ContactGroupCriteria toModel(){
        return ContactGroupCriteria.builder()
                .groupName(groupName)
                .build();
    }
}
