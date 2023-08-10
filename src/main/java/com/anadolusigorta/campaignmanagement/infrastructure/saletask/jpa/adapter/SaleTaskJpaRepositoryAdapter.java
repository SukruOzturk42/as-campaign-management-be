/* dks20165 created on 31.01.2022 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.saletask.model.SaleTask;
import com.anadolusigorta.campaignmanagement.domain.saletask.port.SaleTaskRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.entity.SaleTaskEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.respository.SaleTaskJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SaleTaskJpaRepositoryAdapter implements SaleTaskRepository {

	private SaleTaskJpaRepository saleTaskJpaRepository;

	@Override
	public List<SaleTask> findByAgencyCode(String agencyCode) {

		return saleTaskJpaRepository.findByAgencyAgencyCode(agencyCode)
				.stream()
				.map(SaleTaskEntity::toModel)
				.collect(Collectors.toList());
	}
}
