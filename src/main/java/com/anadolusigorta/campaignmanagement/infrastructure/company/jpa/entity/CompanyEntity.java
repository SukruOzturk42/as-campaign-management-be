/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository */

package com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.company.model.Company;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "company")
public class CompanyEntity  extends AbstractEntity {

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;

	@Column(name = "description", nullable = false, length = 400)
	private String description;

	public Company toModel(){
		return Company.builder()
				.id(super.getId())
				.name(name)
				.description(description)
				.build();
	}
}
