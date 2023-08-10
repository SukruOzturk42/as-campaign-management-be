package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "as_policy_sale")
public class AsPolicySaleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name = "customer_no")
    private String customerNo;


    @Column(name = "policy_type")
    private Long policyType;


    @Column(name = "policy_number")
    private String policyNumber;


    @Column(name = "proposal_number")
    private String proposalNumber;

    @Column(name = "policy_owner_agency_no")
    private String policyOwnerAgencyNo;


    @Column(name = "sale_date")
    private LocalDateTime saleDate;


    @Column(name = "subject_value")
    private String subjectValue;


    @Column(name = "enabled", nullable = false)
    private boolean enabled=true;


    @Column(name = "sales_channel")
    private  String salesChannel;


    @Column(name = "sks")
    private String sks;


    @Column(name = "msu")
    private String msu;


    @Column(name = "production_directorate")
    private String productionDirectorate;


    @Column(name = "policy_premium" , precision = 18, scale = 6)
    private BigDecimal policyPremium;


    @Column(name = "policy_status", nullable = false)
    private boolean policyStatus = true;


    @Column(name = "distribution_channel")
    private String distributionChannel;

    @Column(name = "subject_type")
    private String subjectType;
}
