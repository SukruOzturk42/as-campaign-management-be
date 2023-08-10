package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response;


import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleReport;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CmTask;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateInformation;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskType;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response.SaleReportResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

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

    private LocalDateTime periodStartDate;

    private LocalDateTime expireDate;

    private String policyNumber;

    private TaskStateInformation taskStateInformation;

    private String renewalNumber;

    private String proposalNumber;

    private String offerNumber;

    private String oldPolicyNumber;

    private String taskOwnerUserName;

    private String taskOwnerName;

    private Boolean isAgencyUserAuth;

    private String policyAgencyNo;


    public static TaskResponse fromModel(CmTask task) {
        return TaskResponse.builder()
                .id(task.getId())
                .knimeTaskId(task.getKnimeTaskId())
                .customerNo(task.getCustomerNo())
                .agencyNo(task.getAgencyNo())
                .agencyTitle(task.getAgencyTitle())
                .saleChannel(task.getSaleChannel())
                .subjectValue(task.getSubjectValue())
                .subjectType(task.getSubjectType())
                .policyType(task.getPolicyType())
                .policyBranch(task.getPolicyBranch())
                .customerName(task.getCustomerName())
                .customerPhone(task.getCustomerPhone())
                .customerEmail(task.getCustomerEmail())
                .customerType(task.getCustomerType())
                .regionCode(task.getRegionCode())
                .taskType(task.getTaskType())
                .periodStartDate(task.getPeriodStartDate())
                .expireDate(task.getExpireDate())
                .policyNumber(task.getPolicyNumber())
                .proposalNumber(task.getProposalNumber())
                .taskStateInformation(task.getTaskStateInformation())
                .renewalNumber(task.getRenewalNumber())
                .offerNumber(task.getOfferNumber())
                .oldPolicyNumber(task.getOldPolicyNumber())
                .taskOwnerName(task.getTaskOwnerName())
                .taskOwnerUserName(task.getTaskOwnerUserName())
                .isAgencyUserAuth(task.getIsAgencyUserAuth())
                .policyAgencyNo(task.getPolicyAgencyNo())
                .build();
    }

    public static PageContent<TaskResponse> fromListOfModel(PageContent<CmTask> task) {
        return PageContent.<TaskResponse>builder()
                .content(task.getContent().stream()
                        .map(TaskResponse::fromModel)
                        .collect(Collectors.toList()))
                .page(task.getPage()).size(task.getSize()).totalItems(task.getTotalItems())
                .build();
    }
}
