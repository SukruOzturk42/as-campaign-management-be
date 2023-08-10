package com.anadolusigorta.campaignmanagement.infrastructure.common.jpa;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CustomerCampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {


}