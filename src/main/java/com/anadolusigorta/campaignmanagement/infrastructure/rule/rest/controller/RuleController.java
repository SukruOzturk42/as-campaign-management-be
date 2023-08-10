package com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.rule.facade.RuleFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.adapter.RuleCampaignAttributeRepositoryJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request.CreateRuleRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.request.RuleCriteriaRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.response.RuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiManagementController
@RequiredArgsConstructor
public class RuleController extends BaseController {

    private final RuleFacade ruleFacade;
    private final RuleCampaignAttributeRepositoryJpaAdapter ruleCampaignAttributeRepositoryJpaAdapter;

    @PostMapping(value = "cm-rule/rule")
    public Response<?> createRule(@RequestBody CreateRuleRequest createRuleRequest) {
        ruleFacade.saveRule(createRuleRequest.toModel());
        return respond(HttpStatus.OK);
    }

    @GetMapping(value = "cm-rule/rules")
    public Response<?> getRules() {
        return respond(RuleResponse.fromListOfModel(ruleFacade.getRules()));
    }

    @PostMapping("cm-rule/pageable-rules")
    public PageableResponse<?> getPageableRules(@RequestBody @Valid RuleCriteriaRequest ruleCriteriaRequest,
                                                        @NotNull final Pageable pageable) {
        return respond(RuleResponse.fromPageOfModel(ruleFacade.getPageableRules(ruleCriteriaRequest.toModel(),pageable)));
    }

    @GetMapping(value = "cm-rule/rules-by-campaignTypeId")
    public Response<?> getRulesByCampaignTypeId(@RequestParam Long campaignTypeId) {
        return respond(RuleResponse.fromListOfModel(ruleFacade.getRulesByCampaignTypeId(campaignTypeId)));
    }

    @GetMapping(value = "cm-rule/rule-detail")
    public Response<?> getRuleDetailByRuleId(@RequestParam Long ruleGroupId) {
        return respond(ruleCampaignAttributeRepositoryJpaAdapter.getRuleDetailResponseList(ruleFacade.getRuleByRuleId(ruleGroupId)));
    }
}
