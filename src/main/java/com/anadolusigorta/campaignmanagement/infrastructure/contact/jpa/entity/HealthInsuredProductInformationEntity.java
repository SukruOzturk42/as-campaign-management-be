package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "health_insured_product_information")
public class HealthInsuredProductInformationEntity implements Serializable {

	@Id
	@Column(name = "id",unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contact_number", nullable = false)
	private String contactNumber;

	@Column(name = "policy_code")
	private Long policyCode;

	@Column(name = "policy_create_date")
	private LocalDate policyCreateDate;

	@Column(name = "etl_date")
	private LocalDate etlDate;

}
