package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.scheduler.taskmanagement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "task.management")
public class TaskManagementSchedulerConfigurationProperties {

    private boolean robotJobEnabled;
    private boolean  saleJobEnabled;
    private boolean  expiredTaskJobEnabled;
    private List<String> digitalAgencies;
}
