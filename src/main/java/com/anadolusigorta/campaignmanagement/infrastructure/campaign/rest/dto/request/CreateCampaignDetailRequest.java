package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;
import com.anadolusigorta.campaignmanagement.domain.file.model.CampaignFile;
import com.anadolusigorta.campaignmanagement.infrastructure.file.rest.dto.request.CampaignFileRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignDetailRequest {

    private Long id;

    @NotNull
    private Long referenceTypeId;

    @NotNull
    @Pattern(regexp = "https?://(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?://(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,}",
            message = "Kampanya Linkini Uygun Formatta Giriniz.")
    private String campaignLink;

    private Long campaignId;

    @NotNull
    private String campaignText;

    //@NotNull
    private CampaignFileRequest file;

    private String photo;

    public CampaignDetail toModel() {
        return CampaignDetail.builder()
                .id(id)
                .referenceTypeId(referenceTypeId)
                .campaignLink(campaignLink)
                .file(file==null ? null : file.toModel())
                .campaignId(campaignId)
                .campaignText(campaignText)
                .build();
    }

}
