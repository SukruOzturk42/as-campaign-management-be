package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.scheduler.policysalereward;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "policy.sale.reward")
public class PolicySaleRewardGiftSchedulerConfigurationProperties {
    private boolean jobEnabled;
    private String giftTemplateId;
    private String giftCodeTemplateId;
    private String giftSmsTemplateId;
    private String giftCodeSmsTemplateId;
    private Mail mail;

    @Getter
    @Setter
    @Configuration
    public static class Mail{
        private List<String> toList;
        private List<String> ccList;
    }
}
