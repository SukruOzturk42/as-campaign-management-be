/* odeon_sukruo created on 25.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.common.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.common.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditingEntityListener {
	@PrePersist
	@PreUpdate
	@PreRemove
	public void init(AbstractEntity baseEntity) {

		if(baseEntity.getId()!=null){
			baseEntity.setUpdatedAt(LocalDateTime.now());
		}else{
			baseEntity.setUpdatedAt(LocalDateTime.now());
			baseEntity.setCreatedAt(LocalDateTime.now());

		}


	}
}
