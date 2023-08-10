package com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.saletask.model.Agency;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agency")
public class AgencyEntity extends AbstractEntity {


    @Column(name = "agency_code")
    private String agencyCode;

    @Column(name = "agency_name")
    private String agencyName;

    @OneToMany(mappedBy = "agency",cascade = CascadeType.ALL)
    private Set<SaleTaskEntity> saleTasks;



    public Agency toModel() {
        return Agency.builder()
                .agencyCode(agencyCode)
                .agencyName(agencyName)
                .build();
    }
}
