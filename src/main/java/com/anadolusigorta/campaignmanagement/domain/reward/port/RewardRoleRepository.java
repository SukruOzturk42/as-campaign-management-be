/* odeon_sukruo created on 23.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.reward.port */

package com.anadolusigorta.campaignmanagement.domain.reward.port;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRoleRepository {
	List<RewardRole> findAll();
}
