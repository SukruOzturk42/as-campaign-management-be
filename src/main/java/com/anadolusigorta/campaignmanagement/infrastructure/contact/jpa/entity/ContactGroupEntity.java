package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.model.CreateContactGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contact_group")
public class ContactGroupEntity extends AbstractEntity {

    @Size(max = 64, message = "Group name is too long")
    @Column(name = "group_name", unique = true, nullable = false)
    private String groupName;

    @OneToMany(mappedBy = "contactGroup" ,cascade = CascadeType.ALL)
    private Set<ContactEntity> contacts;

    public ContactGroup toModel() {
        return ContactGroup.builder()
                .contactGroupId(getId())
                .groupName(groupName)
                .createdAt(getCreatedAt())
                .build();
    }



}
