/* dks20165 created on 31.01.2022 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.respository */

package com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.respository;

import com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.entity.SaleTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleTaskJpaRepository extends JpaRepository<SaleTaskEntity, Long> {

	List<SaleTaskEntity>  findByAgencyAgencyCode(String agencyCode);
}
