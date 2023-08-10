package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.notification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "notification.approval.mail")
public class NotificationSendApprovalMailConfigurationProperties {

    private Mail mail;
    private String templateId;
    private String approvalTemplateId;
    private String rejectTemplateId;

    @Getter
    @Setter
    @Configuration
    public static class Mail{
        private List<String> toList;
        private List<String> ccList;

        private List<String> users;
    }

}
