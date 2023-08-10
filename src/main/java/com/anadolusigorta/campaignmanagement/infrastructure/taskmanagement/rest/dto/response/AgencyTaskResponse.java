package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CmTask;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyGroupTask;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyTaskResponse {

    private String agencyCode;

    private Long totalTask;

    private Long totalOpenTask;

    private Long totalClosedTask;

    private List<AgencyProductResponse> products;

    public static AgencyTaskResponse fromModel(List<PolicyGroupTask> tasks,String agencyCode) {

        var totalTasks=tasks.stream()
                .map(PolicyGroupTask::getTasks)
                .flatMap(Collection::stream)
                .toList();
        return AgencyTaskResponse.builder()
                .agencyCode(agencyCode)
                .totalTask(!totalTasks.isEmpty() ? (long) totalTasks.size() : 0)
                .totalOpenTask(!totalTasks.isEmpty() ? (long) totalTasks.stream()
                        .filter(i -> i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_OPEN) ||
                                i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_ON_PROGRESS)).toList().size() : 0)
                .totalClosedTask(!totalTasks.isEmpty() ? (long) totalTasks.stream()
                        .filter(i -> (i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_CLOSED_SUCCESS) ||
                                i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_CLOSED_FAIL))).toList().size() : 0)
                .products(!tasks.isEmpty() ? AgencyProductResponse.fromListOfModel(tasks) : null)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AgencyProductResponse {

        private String productName;

        private List<Long> policyTypes;

        private TaskInformationResponse taskInformation;

        private Boolean priority;

        public static AgencyProductResponse fromModel(PolicyGroupTask policyGroupTask) {

            return AgencyProductResponse.builder()
                    .productName(policyGroupTask.getPolicyGroup().getName())
                    .policyTypes(policyGroupTask.getPolicyGroup().getPolicyGroupTypes()
                            .stream()
                            .map(t->Long.valueOf(t.getName().trim())).toList())
                    .taskInformation(TaskInformationResponse.fromModel(policyGroupTask.getTasks()))
                    .priority(policyGroupTask.getPolicyGroup().getPriority())
                    .build();
        }

        public static List<AgencyProductResponse> fromListOfModel(List<PolicyGroupTask> tasks) {

            var productList = new ArrayList<AgencyProductResponse>();
            tasks.forEach(i -> productList.add(fromModel(i)));

            return productList;

        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskInformationResponse {

        private Long openTasks;

        private Long closedTask;

        private Long successClosedTasks;

        private Long failureClosedTasks;

        public static TaskInformationResponse fromModel(List<CmTask> tasks) {
            return TaskInformationResponse.builder()
                    .openTasks((long) tasks.stream()
                            .filter(i -> i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_OPEN) ||
                                    i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_ON_PROGRESS) ).toList().size())
                    .closedTask((long) tasks.stream()
                            .filter(i -> (i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_CLOSED_SUCCESS) ||
                                    i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_CLOSED_FAIL))).toList().size())
                    .successClosedTasks((long) tasks.stream()
                            .filter(i -> i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_CLOSED_SUCCESS)).toList().size())
                    .failureClosedTasks((long) tasks.stream()
                            .filter(i -> i.getTaskStateInformation().getTaskState().getName().equals(Constants.TASK_STATE_CLOSED_FAIL)).toList().size())
                    .build();
        }
    }

}
