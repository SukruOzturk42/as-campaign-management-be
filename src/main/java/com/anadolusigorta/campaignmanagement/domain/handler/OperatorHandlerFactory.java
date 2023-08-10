package com.anadolusigorta.campaignmanagement.domain.handler;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OperatorHandlerFactory {

    private final OperatorHandler operatorHandler;
    public Boolean isMatchParam(Object requestValueObject, CampaignRuleAttribute campaignRuleAttribute) {
        try {
            var requestValue = requestValueObject.toString();
            return switch (campaignRuleAttribute.getDataType()) {
                case DATE, DATETIME -> handleDateTypeMatchParam(campaignRuleAttribute, requestValue);
                default -> handleDefaultTypeMatchParam(campaignRuleAttribute, requestValue);
            };
        } catch (Exception e) {
            log.info(e.getMessage());
            return Boolean.FALSE;
        }
    }

    private Boolean handleDefaultTypeMatchParam(CampaignRuleAttribute campaignRuleAttribute, String requestValue) {
        return switch (campaignRuleAttribute.getOperator()) {
            case EQ -> operatorHandler.handleEQOperator(requestValue, campaignRuleAttribute.getValue());
            case NEQ -> operatorHandler.handleNEQOperator(requestValue, campaignRuleAttribute.getValue());
            case IN -> operatorHandler.handleINOperator(requestValue, campaignRuleAttribute.getValue());
            case GTE -> operatorHandler.handleGTEOperator(requestValue, campaignRuleAttribute.getValue());
            case LTE -> operatorHandler.handleLTEOperator(requestValue, campaignRuleAttribute.getValue());
            case GT -> operatorHandler.handleGTOperator(requestValue, campaignRuleAttribute.getValue());
            case LT -> operatorHandler.handleLTOperator(requestValue, campaignRuleAttribute.getValue());
            case NIN -> operatorHandler.handleNINOperator(requestValue, campaignRuleAttribute.getValue());
            case BETWEEN -> operatorHandler.handleBetweenOperator(requestValue, campaignRuleAttribute.getValue());
            case AGE -> operatorHandler.handleAGEOperator(requestValue, campaignRuleAttribute.getValue());
            case EQ_BIRTH_DATE -> operatorHandler.handleBirthDateOperator(requestValue);
            default -> Boolean.FALSE;
        };
    }

    private Boolean handleDateTypeMatchParam(CampaignRuleAttribute campaignRuleAttribute, String requestValue) {
        return switch (campaignRuleAttribute.getOperator()) {
            case EQ_BIRTH_DATE -> operatorHandler.handleBirthDateOperator(requestValue);
            case BETWEEN -> operatorHandler.handleBetweenDateOperator(requestValue, campaignRuleAttribute.getValue());
            case GT -> operatorHandler.handleGTDateOperator(requestValue, campaignRuleAttribute.getValue());
            case LT -> operatorHandler.handleLTDateOperator(requestValue, campaignRuleAttribute.getValue());
            default -> Boolean.FALSE;
        };
    }
}
