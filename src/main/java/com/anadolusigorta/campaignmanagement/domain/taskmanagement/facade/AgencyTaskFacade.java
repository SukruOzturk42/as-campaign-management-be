package com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CmTask;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyGroupTask;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskCriteria;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.CmTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyTaskFacade {

    private final CmTaskRepository cmTaskRepository;
    private final PolicyGroupFacade policyGroupFacade;

    public List<PolicyGroupTask> getAgencyTasksByAgencyCode(String agencyCode) {
        var criteria = new TaskCriteria();
        criteria.setAgencyCodes(new ArrayList<>());
        criteria.getAgencyCodes().add(agencyCode);

        var pageable = PageRequest.of(1,Integer.MAX_VALUE);
        var tasks= cmTaskRepository.findByCriteria(criteria, pageable).getContent();
        var policyGroups=policyGroupFacade.getAllPolicyGroups();

       List<PolicyGroupTask> policyGroupTasks=new ArrayList<>();

       for(var pg:policyGroups){
           var pgPolicyTypes=pg.getPolicyGroupTypes().stream().map(t->Long.valueOf(t.getName().trim())).toList();
           var filteredTasks=tasks.stream()
                   .filter(item->pgPolicyTypes.contains(item.getPolicyType()))
                   .toList();
           var agencyPolicyGroupTask=PolicyGroupTask.builder()
                   .policyGroup(pg)
                   .tasks(filteredTasks)
                   .build();
           policyGroupTasks.add(agencyPolicyGroupTask);
       }
       return policyGroupTasks;
    }

    public List<PolicyGroupTask> getAgencyTasksByCustomerNo(String customerNo) {
        var criteria = new TaskCriteria();
        criteria.setContactNumber(customerNo);

        var pageable = PageRequest.of(1,Integer.MAX_VALUE);
        var tasks= cmTaskRepository.findByCriteria(criteria, pageable).getContent();
        var policyGroups=policyGroupFacade.getAllPolicyGroups();

        List<PolicyGroupTask> policyGroupTasks=new ArrayList<>();

        for(var pg:policyGroups){
            var pgPolicyTypes=pg.getPolicyGroupTypes().stream().map(t->Long.valueOf(t.getName().trim())).toList();
            var filteredTasks=tasks.stream()
                    .filter(item->pgPolicyTypes.contains(item.getPolicyType()))
                    .toList();
            var agencyPolicyGroupTask=PolicyGroupTask.builder()
                    .policyGroup(pg)
                    .tasks(filteredTasks)
                    .build();
            policyGroupTasks.add(agencyPolicyGroupTask);
        }

        return policyGroupTasks;
    }
}
