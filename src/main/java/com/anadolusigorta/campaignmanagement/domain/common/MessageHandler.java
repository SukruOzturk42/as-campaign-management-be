package com.anadolusigorta.campaignmanagement.domain.common;


import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.repository.BusinessMessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final MessageSource messageSource;

    private final BusinessMessageJpaRepository businessMessageJpaRepository;

    public String retrieveMessage(String key,String... args) {
        String message = businessMessageJpaRepository.findByKey(key)
                .orElseThrow(()-> new CampaignManagementException("business.message.not.found","key",key)).getDescription();
        String code = businessMessageJpaRepository.findByKey(key)
                .orElseThrow(()-> new CampaignManagementException("business.message.not.found","key",key)).getCode();
        if(args != null){
            for(int i=0;i<args.length;i++){
                if(message != null && args[i]!=null){
                    message = message.replace("{" + i + "}",args[i]);
                }
            }
        }
        return message;
    }


}
