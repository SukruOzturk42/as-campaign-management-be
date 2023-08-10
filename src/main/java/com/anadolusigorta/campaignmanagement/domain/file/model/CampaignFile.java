package com.anadolusigorta.campaignmanagement.domain.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFile {

    private String data;

    private String fileName;

    private Long size;

    private String fileType;

}
