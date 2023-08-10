package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CmTask;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
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
@Table(name = "cm_task")
public class TaskEntity extends AbstractEntity {


    @Column(name = "knime_task_id")
    private String knimeTaskId;

    @Column(name = "customer_no")
    private String customerNo;

    @Column(name = "agency_no")
    private String agencyNo;

    @Column(name = "policy_agency_no")
    private String policyAgencyNo;

    @Column(name = "agency_title")
    private String agencyTitle;

    @Column(name = "sale_channel")
    private String saleChannel;

    @Column(name = "subject_value")
    private String subjectValue;

    @Column(name = "subject_type")
    private String subjectType;

    @Column(name = "policy_type")
    private Long policyType;

    @Column(name = "policy_branch")
    private String policyBranch;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "region_code")
    private Long regionCode;

    @ManyToOne
    @JoinColumn(name = "task_type_id", nullable = false)
    private KnimeTaskTypeEntity taskType;

    @ManyToOne
    @JoinColumn(name = "task_state_information_id")
    private TaskStateInformationEntity taskStateInformation;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "renewal_number")
    private String renewalNumber;

    @Column(name = "proposal_number")
    private String proposalNumber;

    @Column(name = "offer_number")
    private String offerNumber;

    @Column(name = "old_policy_number")
    private String oldPolicyNumber;

    @Column(name = "task_owner_user_name")
    private String taskOwnerUserName;

    @Column(name = "task_owner_name")
    private String taskOwnerName;

    @Column(name = "as_policy_sale_id")
    private Long asPolicySaleId;

    @Column(name = "task_group_id")
    private Long taskGroupId;

    @Column(name = "regional_sales_depart")
    private String regionalSalesDepart;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "msu_name")
    private String msuName;

    @Column(name = "sks")
    private String sks;

    @Column(name = "distribution_channel")
    private String distributionChannel;

    @Column(name = "production_directorate")
    private String productionDepartment;


    @Column(name = "policy_premium" , precision = 18, scale = 6)
    private BigDecimal policyPremium;

    @Column(name = "role")
    private String role;

    @Column(name = "master_backup_msu")
    private String masterBackupMsu;

    @Column(name = "old_renewal_number")
    private String oldRenewalNumber;

    @Column(name = "policy_end_date")
    private LocalDateTime policyEndDate;

    @Column(name = "period_start_date")
    private LocalDateTime periodStartDate;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    public CmTask toModel(){

        return CmTask.builder()
                .id(getId())
                .knimeTaskId(knimeTaskId)
                .customerName(customerName)
                .customerEmail(customerEmail)
                .customerPhone(customerPhone)
                .customerType(customerType)
                .customerNo(customerNo)
                .agencyNo(agencyNo)
                .agencyTitle(agencyTitle)
                .regionCode(regionCode)
                .policyBranch(policyBranch)
                .policyType(policyType)
                .subjectType(subjectType)
                .subjectValue(subjectValue)
                .saleChannel(saleChannel)
                .taskType(taskType!=null?taskType.toModel():null)
                .expireDate(expireDate)
                .policyNumber(policyNumber)
                .proposalNumber(proposalNumber)
                .taskStateInformation(taskStateInformation != null ?taskStateInformation.toModel() : null)
                .renewalNumber(renewalNumber)
                .offerNumber(offerNumber)
                .oldPolicyNumber(oldPolicyNumber)
                .taskOwnerUserName(taskOwnerUserName)
                .taskOwnerName(taskOwnerName)
                .sks(sks)
                .msuName(msuName)
                .regionalSalesDepart(regionalSalesDepart)
                .registrationNumber(registrationNumber)
                .productionDepartment(productionDepartment)
                .distributionChannel(distributionChannel)
                .policyPremium(policyPremium)
                .masterBackupMsu(masterBackupMsu)
                .periodStartDate(periodStartDate)
                .oldRenewalNumber(oldRenewalNumber)
                .policyEndDate(policyEndDate)
                .role(role)
                .saleDate(saleDate)
                .policyAgencyNo(policyAgencyNo)
                .build();
    }

    public static TaskEntity fromModel(CmTask cmTask) {
        return TaskEntity.builder()
                .knimeTaskId(cmTask.getKnimeTaskId())
                .customerName(cmTask.getCustomerName())
                .customerEmail(cmTask.getCustomerEmail())
                .customerPhone(cmTask.getCustomerPhone())
                .customerType(cmTask.getCustomerType())
                .customerNo(cmTask.getCustomerNo())
                .agencyNo(cmTask.getAgencyNo())
                .agencyTitle(cmTask.getAgencyTitle())
                .regionCode(cmTask.getRegionCode())
                .policyBranch(cmTask.getPolicyBranch())
                .policyType(cmTask.getPolicyType())
                .subjectType(cmTask.getSubjectType())
                .subjectValue(cmTask.getSubjectValue())
                .saleChannel(cmTask.getSaleChannel())
                .taskType(cmTask.getTaskType() != null ?
                        KnimeTaskTypeEntity.fromModel(cmTask.getTaskType()) : null)
                .msuName(cmTask.getMsuName())
                .registrationNumber(cmTask.getRegistrationNumber())
                .regionalSalesDepart(cmTask.getRegionalSalesDepart())
                .distributionChannel(cmTask.getDistributionChannel())
                .policyPremium(cmTask.getPolicyPremium())
                .productionDepartment(cmTask.getProductionDepartment())
                .expireDate(cmTask.getExpireDate())
                .renewalNumber(cmTask.getRenewalNumber())
                .offerNumber(cmTask.getOfferNumber())
                .policyNumber(cmTask.getPolicyNumber())
                .oldPolicyNumber(cmTask.getOldPolicyNumber())
                .taskOwnerUserName(cmTask.getTaskOwnerUserName())
                .taskOwnerName(cmTask.getTaskOwnerName())
                .sks(cmTask.getSks())
                .masterBackupMsu(cmTask.getMasterBackupMsu())
                .periodStartDate(cmTask.getPeriodStartDate())
                .oldRenewalNumber(cmTask.getOldRenewalNumber())
                .policyEndDate(cmTask.getPolicyEndDate())
                .role(cmTask.getRole())
                .saleDate(cmTask.getSaleDate())
                .policyAgencyNo(cmTask.getPolicyAgencyNo())
                .build();
    }

}
