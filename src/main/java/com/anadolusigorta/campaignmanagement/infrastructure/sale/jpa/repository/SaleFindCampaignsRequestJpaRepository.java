package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CustomerCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.FindSaleCampaignsRequestEntity;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SaleFindCampaignsRequestJpaRepository extends JpaRepository<FindSaleCampaignsRequestEntity, Long>, JpaSpecificationExecutor<FindSaleCampaignsRequestEntity> {
    Page<FindSaleCampaignsRequestEntity> findAll(Pageable pageable);

}
