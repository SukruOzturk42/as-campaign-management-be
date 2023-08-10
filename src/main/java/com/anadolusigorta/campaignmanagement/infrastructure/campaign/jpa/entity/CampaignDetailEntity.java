package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;
import com.anadolusigorta.campaignmanagement.domain.file.model.CampaignFile;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.jpa.entity.CampaignSaleChannelTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_detail")
@Where(clause = "is_active = 1")
public class CampaignDetailEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_type_id")
    private ReferenceTypeEntity referenceType;

    @Column(name = "campaign_link")
    private String campaignLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignEntity campaign;

    @Column(name = "campaign_text",length = 6000)
    private String campaignText;

    @Column(name = "file_pid")
    private String filePid;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public CampaignDetail toModel() {
        return CampaignDetail.builder()
                .id(super.getId())
                .referenceType(referenceType!=null?referenceType.toModel():null)
                .campaignLink(campaignLink)
                .filePid(filePid)
                .file(CampaignFile.builder().fileName(fileName).build())
                .createdDate(super.getCreatedAt())
                .campaignId(campaign.getId())
                .campaignText(campaignText)
                .build();
    }

}
