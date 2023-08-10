package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreateTaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.SuccessRateCriteria;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskSuccessRate;
import com.anadolusigorta.campaignmanagement.infrastructure.validation.conditionalvalidation.ConditionalValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConditionalValidation(
        conditionalProperty = "role", values = {"AgencyUser","RegionalUser"},
        requiredProperties = {"taskCriteria"},
        message = "Acente/Bölge kullanıcısı için kod seçimi gerekmektedir.")
public class TaskSuccessRateRequest {

    List<String> taskCriteria;

    String role;

    LocalDateTime first;

    LocalDateTime last;

    public SuccessRateCriteria toModel(){

        if(last.isBefore(first)){
            throw new CampaignManagementException("end.date.cannot.earlier.start.date");
        }

        return SuccessRateCriteria.builder()
                .taskCriteria(taskCriteria)
                .role(role)
                .first(first)
                .last(last)
                .build();

    }

}
