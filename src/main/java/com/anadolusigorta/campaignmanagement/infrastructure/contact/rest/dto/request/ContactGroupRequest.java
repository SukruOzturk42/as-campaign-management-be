package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.model.CreateContactGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactGroupRequest implements Serializable {

    private Long id;

    @NotNull
    private String groupName;

    @NotNull
    private List<ContactNumberRequest> contactNumbers;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContactNumberRequest {
        @JsonProperty("MUSTERI_NO")
        @SerializedName("MUSTERI_NO")
        private String customerNo;
    }

    public CreateContactGroup toModel() {
        return CreateContactGroup.builder()
                .groupName(groupName)
                .contactNumbers(this.contactNumbers != null ? contactNumbers.stream()
                        .map(item-> CreateContactGroup.ContactNumber.builder().customerNo(item.customerNo).build())
                        .toList() :
                        null)
                .build();
    }

    public ContactGroup toModelContactGroup(){
        return  ContactGroup.builder()
                .groupName(groupName)
                .build();

    }

}
