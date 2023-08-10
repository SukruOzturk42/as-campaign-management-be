package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.PolicyGroupFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request.CreatePolicyGroupRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response.PolicyGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiManagementController
@RequiredArgsConstructor
public class PolicyGroupController extends BaseController {

    private final PolicyGroupFacade policyGroupFacade;

    @PostMapping("policy-group")
    public Response<?> savePolicyGroup(@RequestBody @Valid CreatePolicyGroupRequest request) {
        return respond(PolicyGroupResponse.fromModel(policyGroupFacade.save(request.toModel())));
    }

    @GetMapping("policy-groups")
    public PageableResponse<?> getAllPolicyGroups(@NotNull final Pageable pageable){
        return respond(PolicyGroupResponse.fromListOfModel(policyGroupFacade.getAllPolicyGroups(pageable)));
    }

    @GetMapping("policy-group")
    public Response<?> getPolicyGroupById(@RequestParam(name = "id") Long id){
        return respond(PolicyGroupResponse.fromModel(policyGroupFacade.getPolicyGroupById(id)));
    }

    @DeleteMapping("policy-group")
    public void deletePolicyGroupById(@RequestParam(name = "id") Long id){
        policyGroupFacade.deletePolicyGroupById(id);
    }

}
