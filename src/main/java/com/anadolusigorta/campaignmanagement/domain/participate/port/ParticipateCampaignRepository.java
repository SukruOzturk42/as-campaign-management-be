/* dks20165 created on 4.08.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.product.port */

package com.anadolusigorta.campaignmanagement.domain.participate.port;

import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipateCampaign;
import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipateCampaignRepository {

	ParticipatedCustomerCampaign save(ParticipateCampaign participateCampaign);


	 Integer gerNumberOfParticipation(ParticipateCampaign participateCampaign);


}
