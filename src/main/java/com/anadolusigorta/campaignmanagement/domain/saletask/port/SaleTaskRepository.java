/* dks20165 created on 31.01.2022 inside the package - com.anadolusigorta.campaignmanagement.domain.saletask.port */

package com.anadolusigorta.campaignmanagement.domain.saletask.port;

import com.anadolusigorta.campaignmanagement.domain.saletask.model.SaleTask;

import java.util.List;

public interface SaleTaskRepository {

	List<SaleTask>  findByAgencyCode(String agencyCode);
}
