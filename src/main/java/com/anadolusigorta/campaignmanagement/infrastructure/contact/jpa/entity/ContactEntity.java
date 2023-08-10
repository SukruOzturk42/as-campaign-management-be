package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.contact.model.Contact;
import com.anadolusigorta.campaignmanagement.domain.contact.model.CreateContactGroup;
import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contact")
public class ContactEntity extends AbstractEntity {

    @Column(name = "contact_no")
    private String contactNo;


    @ManyToOne
    private ContactGroupEntity contactGroup;


    public Contact toModel() {
        return Contact.builder()
                .contactNo(contactNo)
                .build();
    }

    public static ContactEntity toEntity(CreateContactGroup.ContactNumber contactNumber, ContactGroupEntity contactGroupEntity){
        var entity=new ContactEntity();
        entity.setContactNo(contactNumber.getCustomerNo());
        entity.setContactGroup(contactGroupEntity);
        return entity;
    }

}
