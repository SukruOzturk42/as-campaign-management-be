package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CmTask;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "knime_task")
public class KnimeTaskEntity  {

    @Id
    @Column(name = "knime_task_id",unique = true)
    private String id;


    @Column(name = "customer_no")
    private String customerNo;

    @Column(name = "agency_no")
    private String agencyNo;

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

    @Column(name = "agency_region_code")
    private String agencyRegionCode;

    @Column(name = "production_department")
    private String productionDepartment;

    @Column(name = "sks")
    private String sks;

    @Column(name = "regional_sales_depart")
    private String regionalSalesDepart;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "msu_name")
    private String msuName;

    @Column(name = "msu_last_name")
    private String msuLastName;

    @Column(name = "role")
    private String role;

    @Column(name = "master_backup_msu")
    private String masterBackupMsu;

    @Column(name = "is_transferred")
    private Boolean isTransferred;

    @Column(name = "old_policy_number")
    private Long oldPolicyNumber;

    @Column(name = "old_renewal_number")
    private String oldRenewalNumber;

    @CreatedDate
    @Column(name = "load_date", length = 400)
    private LocalDateTime loadDate;

    @ManyToOne
    @JoinColumn(name = "task_type_id", nullable = false)
    private KnimeTaskTypeEntity taskType;

    @Column(name = "policy_end_date")
    private LocalDateTime policyEndDate;

    @Column(name = "period_start_date")
    private LocalDateTime periodStartDate;


    public CmTask toCmTaskModel() {
        return CmTask.builder()
                .knimeTaskId(id)
                .customerNo(customerNo)
                .agencyNo(agencyNo)
                .agencyTitle(agencyTitle)
                .saleChannel(saleChannel)
                .subjectValue(subjectValue)
                .subjectType(subjectType)
                .policyType(policyType)
                .policyBranch(policyBranch)
                .customerName(customerName)
                .customerPhone(customerPhone)
                .customerEmail(customerEmail)
                .customerType(customerType)
                .regionCode(agencyRegionCode != null ? Long.valueOf(agencyRegionCode) : null)
                .msuName(msuName +" "+ msuLastName)
                .registrationNumber(registrationNumber)
                .regionalSalesDepart(regionalSalesDepart)
                .taskType(taskType != null ? taskType.toModel() : null)
                .oldPolicyNumber(oldPolicyNumber != null ? oldPolicyNumber.toString() : null)
                .periodStartDate(periodStartDate)
                .role(role)
                .policyEndDate(policyEndDate)
                .oldRenewalNumber(oldRenewalNumber)
                .masterBackupMsu(masterBackupMsu)
                .productionDepartment(productionDepartment)
                .sks(sks)
                .build();
    }
}
