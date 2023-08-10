package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contact_portfolio_information")
public class ContactPortfolioInformationEntity implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contact_number", nullable = false, unique = true)
	private String contactNumber;

	private LocalDateTime portfolioStartDate;

	private LocalDate etlDate;

}
