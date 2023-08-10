package com.anadolusigorta.campaignmanagement.infrastructure.campaignflow.checkcodestatus.entity;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "check_code_status_flow")
public class CheckCodeStatusFlowEntity extends AbstractEntity {

    private String parametersTobeChecked;

}
