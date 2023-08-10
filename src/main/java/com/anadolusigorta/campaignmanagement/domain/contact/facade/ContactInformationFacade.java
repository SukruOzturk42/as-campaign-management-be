package com.anadolusigorta.campaignmanagement.domain.contact.facade;


import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactInformationSearch;
import com.anadolusigorta.campaignmanagement.domain.ownerproduct.port.SearchPolicyOwnerProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactInformationFacade {

    private final SearchPolicyOwnerProductPort searchPolicyOwnerProductPort;


    public Map<String,Object> getContactInformation(ContactInformationSearch contactInformationSearch){
        List<Callable<Map<String,Object>>> callableTasks = new ArrayList<>();
       var results = new HashMap<String,Object>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        callableTasks.add(()->searchPolicyOwnerProductPort
                .getOwnerProductFromSearchPolicyService(contactInformationSearch.getContactNumber()));



        try {
            var futures=executorService.invokeAll(callableTasks,30,TimeUnit.SECONDS);
          for(var f:futures){
              var r=f.get(3, TimeUnit.SECONDS);
              results.putAll(r);
          }

        } catch (Exception e) {
         log.info("Exception occur"+e.getMessage());
        }
        return results;
    }
}
