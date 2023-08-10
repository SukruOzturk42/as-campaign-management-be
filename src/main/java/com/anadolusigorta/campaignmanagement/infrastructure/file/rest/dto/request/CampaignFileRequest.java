package com.anadolusigorta.campaignmanagement.infrastructure.file.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.file.model.CampaignFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFileRequest {

    private String data;

    private String fileName;

    private Long size;

    private String type;

    public CampaignFile toModel() {
        return CampaignFile.builder()
                .data(data)
                .fileName(fileName)
                .size(size)
                .fileType(type)
                .build();
    }

}
