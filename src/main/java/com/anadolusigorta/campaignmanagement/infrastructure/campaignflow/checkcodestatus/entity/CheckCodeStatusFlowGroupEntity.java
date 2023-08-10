package com.anadolusigorta.campaignmanagement.infrastructure.campaignflow.checkcodestatus.entity;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "check_code_status_group")
public class CheckCodeStatusFlowGroupEntity extends AbstractEntity {

    private String campaignType;

    private String requestType;

    @OneToOne
    @JoinColumn(name = "check_code_status_flow_id")
    private CheckCodeStatusFlowEntity checkCodeStatusFlowEntity;

    private int runOrder;
}
