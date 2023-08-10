package com.anadolusigorta.campaignmanagement.infrastructure.login.jpa.adapter;

import org.springframework.stereotype.Service;

import com.anadolusigorta.campaignmanagement.domain.login.port.SingleSignOnRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.login.jpa.entity.SingleSignOn;
import com.anadolusigorta.campaignmanagement.infrastructure.login.jpa.repository.SingleSignOnJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SingleSignOnJpaAdapter implements SingleSignOnRepository {

	private final SingleSignOnJpaRepository singleSignOnJpaRepository;

	@Override
	public SingleSignOn checkSingleSignOn(String hashValue) {
		var response = singleSignOnJpaRepository.findByHashValueAndValidTrue(hashValue);
		return response.orElse(null);
	}

	@Override
	public void setPassive(SingleSignOn singleSignOn) {
		singleSignOn.setValid(false);
		singleSignOnJpaRepository.save(singleSignOn);
	}
}
