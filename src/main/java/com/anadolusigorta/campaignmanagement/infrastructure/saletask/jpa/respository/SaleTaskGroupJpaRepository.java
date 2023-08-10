/* dks20165 created on 31.01.2022 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.respository */

package com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.respository;

import com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.entity.SaleTaskGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleTaskGroupJpaRepository extends JpaRepository<SaleTaskGroupEntity, Long> {
}
