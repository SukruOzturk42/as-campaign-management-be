package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.KnimeTaskTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskStateInformationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmTask {


    private Long id;

    private String knimeTaskId;

    private String customerNo;

    private String agencyNo;

    private String agencyTitle;

    private String saleChannel;

    private String subjectValue;

    private String subjectType;

    private Long policyType;

    private String policyBranch;

    private String customerName;

    private String customerPhone;

    private String customerEmail;

    private String customerType;

    private Long regionCode;

    private TaskType taskType;

    private LocalDateTime expireDate;

    private String policyNumber;

    private String proposalNumber;

    private TaskStateInformation taskStateInformation;

    private String renewalNumber;

    private String offerNumber;

    private String oldPolicyNumber;

    private String taskOwnerUserName;

    private String taskOwnerName;

    private String regionalSalesDepart;

    private String registrationNumber;

    private String msuName;

    private LocalDateTime periodStartDate;

    private String sks;

    private String distributionChannel;

    private String productionDepartment;

    private BigDecimal policyPremium;

    private String role;

    private String masterBackupMsu;

    private String oldRenewalNumber;

    private LocalDateTime policyEndDate;

    private LocalDateTime saleDate;

    private Boolean isAgencyUserAuth;

    private String policyAgencyNo;

}
