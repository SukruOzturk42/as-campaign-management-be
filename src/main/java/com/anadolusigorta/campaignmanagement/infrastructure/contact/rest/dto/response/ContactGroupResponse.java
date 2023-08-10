package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.DiscountCodeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactGroupResponse {

    private Long contactGroupId;
    private String groupName;
    private LocalDateTime createdAt;


    public static ContactGroupResponse fromModel(ContactGroup  contactGroup) {
        return ContactGroupResponse.builder()
                .contactGroupId(contactGroup.getContactGroupId())
                .groupName(contactGroup.getGroupName())
                .createdAt(contactGroup.getCreatedAt())
                .build();
    }

    public static List<ContactGroupResponse> fromListModel(List<ContactGroup>  contactGroups) {
        return contactGroups.stream()
                .map(ContactGroupResponse::fromModel)
                .collect(Collectors.toList());
    }

    public static PageContent<ContactGroupResponse> fromListOfModel(PageContent<ContactGroup> contactGroups){
        return PageContent.<ContactGroupResponse>builder()
                .content(fromListModel(contactGroups.getContent()))
                .page(contactGroups.getPage())
                .size(contactGroups.getSize())
                .totalItems(contactGroups.getTotalItems())
                .totalPages(contactGroups.getTotalPages())
                .build();
    }



}
