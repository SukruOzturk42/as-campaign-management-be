/* odeon_sukruo created on 23.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.reward.port */

package com.anadolusigorta.campaignmanagement.domain.reward.port;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardNotificationStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardNotificationStatusRepository {

	RewardNotificationStatus findByCode(String code);

}
