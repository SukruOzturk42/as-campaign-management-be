package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.CheckCodeRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.FindSaleCampaignsRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CheckCodeRequestJpaRepository extends JpaRepository<CheckCodeRequestEntity, Long>, JpaSpecificationExecutor<CheckCodeRequestEntity> {

}
