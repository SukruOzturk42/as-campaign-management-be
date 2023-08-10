package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeKindEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.enums.UploadFileEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount_code_information")
public class DiscountCodeInformationEntity extends AbstractEntity {

    @Column(name = "code_group_name")
    private String codeGroupName;

    @ManyToOne
    @JoinColumn(name = "code_type_id", nullable = false)
    private CodeTypeEntity codeType;

    @ManyToOne
    @JoinColumn(name = "code_kind_id", nullable = false)
    private CodeKindEntity codeKind;

    @Column(name = "is_paired_with_customers")
    private Boolean isPairedWithCustomers;

    @Column(name = "code_character_length")
    private Long codeCharacterLength;

    @Column(name = "code_count")
    private Long codeCount;
    @Column(name = "code_expiration_date")
    private LocalDateTime codeExpirationDate;

    @Enumerated(EnumType.STRING)
    private UploadFileEnum uploadFileStatus;

    @Column(nullable = false)
    private Boolean isUsed = false;

    @OneToMany(mappedBy = "discountCodeInformation",cascade = CascadeType.ALL)
    private Set<DiscountCodeEntity> discountCodes;

    @OneToMany(mappedBy="discountCodeInformation" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DiscountCodeInformationDetailEntity> discountCodeInformationDetails = new ArrayList<>();

    @Column(name="upload_start_time")
    private LocalDateTime uploadStartTime;

    @Column(name="upload_finish_time")
    private LocalDateTime uploadFinishTime;

    public DiscountCodeInformation toModel() {
        return DiscountCodeInformation.builder()
                .id(super.getId())
                .codeGroupName(codeGroupName)
                .codeType(codeType.toModel())
                .codeKind(codeKind.toModel())
                .isPairedWithCustomers(isPairedWithCustomers)
                .codeCharacterLength(codeCharacterLength)
                .codeExpirationDate(codeExpirationDate)
                .uploadFileStatus(uploadFileStatus != null ? uploadFileStatus.getValue() : "")
                .isUsed(isUsed)
                .discountCodeInformationDetails(discountCodeInformationDetails
                        .stream()
                        .map(DiscountCodeInformationDetailEntity::toModel)
                        .collect(Collectors.toList()))
                .uploadStartTime(uploadStartTime)
                .uploadFinishTime(uploadFinishTime)
                .codeGeneratedCount(codeCount)
                .build();
    }
}
