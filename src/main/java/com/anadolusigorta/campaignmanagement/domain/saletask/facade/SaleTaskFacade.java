/* dks20165 created on 31.01.2022 inside the package - com.anadolusigorta.campaignmanagement.domain.saletask.facade */

package com.anadolusigorta.campaignmanagement.domain.saletask.facade;

import com.anadolusigorta.campaignmanagement.domain.saletask.model.SaleTask;
import com.anadolusigorta.campaignmanagement.domain.saletask.port.SaleTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleTaskFacade {

	private final SaleTaskRepository saleTaskRepository;

	public List<SaleTask>  getCurrentUserSaleTasks(){

		return saleTaskRepository.findByAgencyCode("320320");

	}
}
