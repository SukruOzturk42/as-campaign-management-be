package com.anadolusigorta.campaignmanagement.domain.sale.facade;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRuleGroupRepository;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.DiscountCodeInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.SaleCodeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.port.CustomerCampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.handler.CampaignRuleGroupHandler;
import com.anadolusigorta.campaignmanagement.domain.handler.RuleAttributeHandler;
import com.anadolusigorta.campaignmanagement.domain.ownerproduct.port.SearchPolicyOwnerProductPort;
import com.anadolusigorta.campaignmanagement.domain.sale.port.SaleCampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.sale.port.SaleCheckFlowRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleCheckFacade {
    private final SaleCheckFlowRepository saleCheckFlowRepository;
    private final CampaignRuleGroupRepository ruleGroupRepository;

    private final CampaignRepository campaignRepository;

    private final CustomerCampaignRepository customerCampaignRepository;

    private final SaleCampaignRepository saleCampaignRepository;

    private final DiscountCodeInformationRepository discountCodeInformationRepository;


    private final SearchPolicyOwnerProductPort searchPolicyOwnerProductPort;

    private final CampaignRuleGroupHandler campaignRuleGroupHandler;

    private final RuleAttributeHandler ruleAttributeHandler;










    CheckSaleStatus checkSaleStatus(CheckSaleStatus checkSaleStatus) {

        var campaignInformation=getCampaignInformation(checkSaleStatus);

        var ruleGroup=ruleGroupRepository.findById(checkSaleStatus.getRuleGroupId());

        var checkCampaignType=getCampaignType(ruleGroup,checkSaleStatus);

        var flowList=saleCheckFlowRepository
                .findAllByCampaignTypeAndRequestTypeOrderByRunOrder(checkCampaignType,checkSaleStatus.getOperationType().getValue());
        for(var flow:flowList){
            switch (flow){
                case LIMIT ->checkLimit(checkSaleStatus.getContactNumber(),campaignInformation);
                case CODE_VALID ->codeValid(checkSaleStatus,ruleGroup);
                case EXT_CODE_VALID -> extCodeValid(checkSaleStatus,ruleGroup);
                case OLD_PROPOSAL -> checkOldProposal(checkSaleStatus,campaignInformation);
                case POLICY_SEARCH ->checkOwnerProduct(checkSaleStatus,ruleGroup);
                case PROPOSAL_ISSUE_DATE -> proposalIssueDateCheck(checkSaleStatus,ruleGroup);
                default -> throw new CampaignManagementException("check.status.flow.not.found");
            }
        }

        return checkSaleStatus;


    }

    public void checkLimit(String contactNo, CampaignInformation campaignInformation) {
        if (Boolean.TRUE.equals(campaignInformation.getHasCustomerLimit())) {
            var limit = campaignInformation.getCustomerLimitSize();

            var contactSaleCount=saleCampaignRepository
                    .contactSaleCountInCampaign(contactNo,campaignInformation.getCampaignId());

            if (contactSaleCount >= limit) {
                throw new CampaignManagementException(ExceptionConstants.CUSTOMER_LIMIT_IS_REACHED,
                        contactNo);
            }
        }
    }

    public void extCodeValid(CheckSaleStatus checkSaleStatus, CampaignRuleGroup ruleGroup) {
        var relatedCorporation = ruleGroup.getRelatedCooperation();
        if (relatedCorporation != null) {
            var relatedCorporationParameter = relatedCorporation.getRules().stream().filter(
                            item -> Constants.RELATED_COOPERATION.equals(item.getParameter()))
                    .toList();
            if (!relatedCorporationParameter.isEmpty()) {
                validateCodeCondition(checkSaleStatus);
                aheConditions(checkSaleStatus, relatedCorporation);
            }
        }

    }

    private static void validateCodeCondition(CheckSaleStatus checkSaleStatus) {
        if (SaleCodeTypeEnum.CODE.equals(checkSaleStatus.getCodeType()) || checkSaleStatus.getCodeType() == null) {
            throw new CampaignManagementException(ExceptionConstants.BAD_CODE_TYPE_FOR_RELATED_CAMPAIGN,
                    checkSaleStatus.getContactNumber());
        }
        if (checkSaleStatus.getCampaignCode() == null) {
            throw new CampaignManagementException(ExceptionConstants.AHE_CODE_NOT_PROVIDED,
                    checkSaleStatus.getContactNumber());
        }
    }

    private void aheConditions(CheckSaleStatus checkSaleStatus,CampaignRuleGroup relatedCorporation){
        boolean result;
        var relatedCorporationParameter = relatedCorporation.getRules().stream().filter(
                        item -> Constants.RELATED_COOPERATION.equals(item.getParameter()))
                .toList();
        var relatedCoop=new ArrayList<String>();
        Map<String,Object> relatedCopParam=new HashMap<>();
        for(var rule:relatedCorporationParameter){
           var relatedAttributeValues=rule.getValue();

        }
        relatedCopParam.put(Constants.RELATED_COOPERATION,relatedCoop);
        relatedCorporation.setRules(relatedCorporationParameter);
        result=campaignRuleGroupHandler.isMatchForProduct(relatedCorporation,relatedCopParam);
        if(!result){
            throw new CampaignManagementException(ExceptionConstants.COULD_NOT_FIND_RELATED_CORPORATION,
                    checkSaleStatus.getContactNumber());
        }

    }


    public void codeValid(CheckSaleStatus checkSaleStatus, CampaignRuleGroup ruleGroup) {
        if (ruleGroup.hasDiscount()) {
            validateCode(checkSaleStatus);
            var discountInformationId = ruleGroup.getCampaignReward().getCampaignRewardDiscount().getDiscountCodeInformation().getId();
            if (ruleGroup.getCampaignReward().getCampaignRewardDiscount().getDiscountCodeInformation().getCodeExpirationDate()
                    .isAfter(LocalDateTime.now())) {

                var code = discountCodeInformationRepository
                        .findDiscountCodeByDiscountCodeInformation(discountInformationId,
                                checkSaleStatus.getCampaignCode())
                        .orElseThrow(() -> new CampaignManagementException(ExceptionConstants.CODE_NOT_FOUND,
                                checkSaleStatus.getContactNumber()));
                codeStatusMatch(checkSaleStatus, code);
                checkSaleStatus.setId(code.getDiscountCodeInformation().getId());
            } else {
                throw new CampaignManagementException(ExceptionConstants.CODE_GROUP_EXPIRED,
                        checkSaleStatus.getContactNumber());
            }
        } else {
            throw new CampaignManagementException(ExceptionConstants.NO_REWARD_DEFINED_FOR_CAMPAIGN);
        }

    }

    private static void validateCode(CheckSaleStatus checkSaleStatus) {
        if (!SaleCodeTypeEnum.CODE.equals(checkSaleStatus.getCodeType())) {
            throw new CampaignManagementException(ExceptionConstants.BAD_CODE_TYPE_FOR_CODE_CAMPAIGN);
        }
        if (checkSaleStatus.getCampaignCode() == null) {
            throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_CODE_NOT_PROVIDED,
                    checkSaleStatus.getContactNumber());
        }
    }

    private void codeStatusMatch(CheckSaleStatus checkSaleStatus, DiscountCode code) {
        if (CodeStatusEnum.USED.equals(code.getCodeStatusEnum())) {
            throw new CampaignManagementException(ExceptionConstants.CODE_USED, checkSaleStatus.getContactNumber());
        } else if (Boolean.FALSE.equals(code.getIsActive())) {
            throw new CampaignManagementException(ExceptionConstants.CODE_PASSIVE, checkSaleStatus.getContactNumber());
        } else if (code.getContactNumber() != null
                && !code.getContactNumber().equals(checkSaleStatus.getContactNumber())) {
            throw new CampaignManagementException(ExceptionConstants.CODE_ASSIGNED_TO_SOMEONE_ELSE,
                    checkSaleStatus.getContactNumber());
        }
    }

    public void checkOldProposal(CheckSaleStatus checkSaleStatus,
                                 CampaignInformation campaignInformation) {
        if (checkSaleStatus.hasProposal()) {
            var hasContactOldProposal = saleCampaignRepository
                    .hasContactOldProposal(checkSaleStatus.getContactNumber()
                            ,checkSaleStatus.getOldProposalNumber()
                            ,checkSaleStatus.getOldRevisionNumber());

            if (hasContactOldProposal) {
                checkSaleStatus.setSuccess(Boolean.TRUE);
            } else {
                log.info(String.format("Check Sale No Proposal With Old Proposal: %s and Old Revision: %s",
                        checkSaleStatus.getOldProposalNumber(), checkSaleStatus.getOldRevisionNumber()));
                throw new CampaignManagementException(ExceptionConstants.NO_PROPOSAL_WITH_OLD_PROPOSAL_AND_OLD_REVISION,
                        checkSaleStatus.getContactNumber());
            }
        } else {
            if (Boolean.TRUE.equals(campaignInformation.isActiveCampaign())) {
                checkSaleStatus.setSuccess(Boolean.TRUE);
            } else {
                throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_NOT_ACTIVE,
                        checkSaleStatus.getContactNumber());
            }
        }
    }

    private void checkOwnerProduct(CheckSaleStatus checkSaleStatus,
                                            CampaignRuleGroup campaignRuleGroup) {
        if (checkSaleStatus.getOldProposalNumber() == null && checkSaleStatus.getOldRevisionNumber() == null) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();


                Callable<Map<String,Object>> task = getOwnerProductCallableTask(checkSaleStatus, campaignRuleGroup);

                Future<Map<String,Object>> future = executorService.submit(task);

                try {
                    Map<String,Object>   result = future.get(15*60, TimeUnit.SECONDS);

                    validateOwnerProduct(checkSaleStatus, campaignRuleGroup, result);

                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException | TimeoutException ee) {
                    throw new CampaignManagementException(ExceptionConstants.TIMEOUT_FOR_SEARCH_POLICY);
                } finally {
                    future.cancel(Boolean.TRUE);
                }

        }
    }

    private void validateOwnerProduct(CheckSaleStatus checkSaleStatus, CampaignRuleGroup campaignRuleGroup, Map<String, Object> contactOwnerProduct) {
        if (contactOwnerProduct != null) {

            var matchResult=campaignRuleGroupHandler
                    .isMatchForProduct(campaignRuleGroup.getOwnerProduct(), contactOwnerProduct);

            if(!matchResult){
                throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_OWNER_PRODUCT_NOT_MATCH,
                        checkSaleStatus.getContactNumber());
            }

            checkSaleStatus.setSuccess(true);

        }else{

            throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_OWNER_PRODUCT_NOT_MATCH,
                    checkSaleStatus.getContactNumber());
        }
    }




    private Callable<Map<String,Object>> getOwnerProductCallableTask(CheckSaleStatus checkSaleStatus, CampaignRuleGroup campaignRuleGroup) {
        return () -> {
            var contactRole = getContactRolesOfOwnerProduct(campaignRuleGroup.getOwnerProduct());

            return searchPolicyOwnerProductPort
                    .getOwnerProductFromSearchPolicyService(checkSaleStatus.getContactNumber()
                            , contactRole
                            , checkSaleStatus.getVinNumber(),campaignRuleGroup.getOwnerProduct());
        };
    }

    public void proposalIssueDateCheck(CheckSaleStatus checkSaleStatus,
                                       CampaignRuleGroup campaignRuleGroup) {
        var ruleAttribute = campaignRuleGroup.getRuleAttribute("policyCreateDate");
        if (ruleAttribute != null) {
            if (checkSaleStatus.getProposalIssueDate() != null) {
                var requestMatchesRule = ruleAttributeHandler.isMatchValue(checkSaleStatus.getProposalIssueDate(),
                        ruleAttribute);
                var systemTimeMatchesRule = ruleAttributeHandler.isMatchValue(LocalDateTime.now(), ruleAttribute);
                if (!requestMatchesRule || !systemTimeMatchesRule) {
                    throw new CampaignManagementException("proposal.issue.date.does.not.match.rule",
                            checkSaleStatus.getContactNumber());
                }
            } else {
                throw new CampaignManagementException("proposal.issue.date.can.not.be.null");
            }

        }
    }



    private CampaignInformation getCampaignInformation(CheckSaleStatus checkSaleStatus){

        var customerCampaign=customerCampaignRepository.findByCampaignId(checkSaleStatus.getCampaignId());
        if(customerCampaign==null){
            return campaignRepository
                    .findByCampaignIdAndCampaignVersionAndRuleGroupId(checkSaleStatus.getCampaignId(),checkSaleStatus.getCampaignVersion(),checkSaleStatus.getRuleGroupId())
                    .getCampaignInformation();
        }
        return customerCampaign.getCampaignInformation();
    }
    private String getCampaignType(CampaignRuleGroup ruleGroup, CheckSaleStatus checkSaleStatus) {
        String campaignType = null;

        if (ruleGroup.getRelatedCooperation() != null) {
            campaignType = Constants.EXT_CODE_CAMPAIGN;
        }
        else if (ruleGroup.getIsBank() != null) {
            campaignType = Constants.IS_BANK_CAMPAIGN;
        }
        else if (ruleGroup.getOwnerProduct() != null) {
            campaignType = Constants.CROSS_SALE_CAMPAIGN;
            if (checkSaleStatus.getCampaignCode() != null) {
                throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_CODE_SHOULD_NOT_BE_PROVIDED,
                        checkSaleStatus.getContactNumber());
            }
        } else if (ruleGroup.getCampaignReward() != null) {
            if (ruleGroup.getCampaignReward().getCampaignRewardDiscount() != null
                    && ruleGroup.getCampaignReward().getCampaignRewardDiscount().getDiscountCodeInformation() != null) {
                campaignType = Constants.DISCOUNT_CODE_CAMPAIGN;
            } else {
                campaignType = Constants.GIFT_OR_NO_DISCOUNT_CAMPAIGN;
                if (checkSaleStatus.getCampaignCode() != null) {
                    throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_CODE_SHOULD_NOT_BE_PROVIDED,
                            checkSaleStatus.getContactNumber());
                }
            }
        }
        return campaignType;
    }

    private List<String> getContactRolesOfOwnerProduct(CampaignRuleGroup ownerProduct) {
        var contactRole = ownerProduct.getRules().stream()
                .filter(item -> item.getParameter().equals(Constants.CONTACT_ROLE_PARAMETER_NAME)).findFirst()
                .orElseThrow(() -> new CampaignManagementException("contact.role.not.found"));

        return contactRole.getValue();

    }
}
