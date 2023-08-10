/* odeon_sukruo created on 23.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.reward.port */

package com.anadolusigorta.campaignmanagement.domain.reward.port;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardCompanyInformationRepository {
	List<RewardCompanyInformation> findAll();

	RewardCompanyInformation save(RewardCompanyInformation rewardCompanyInformation);
}
