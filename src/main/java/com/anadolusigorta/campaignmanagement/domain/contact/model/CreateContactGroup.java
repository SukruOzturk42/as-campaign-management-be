package com.anadolusigorta.campaignmanagement.domain.contact.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactGroup {


    private String groupName;

    private List<ContactNumber> contactNumbers;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContactNumber {

        private String customerNo;
    }


}
