/* dks20165 created on 12.12.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.saletransaction.facade */

package com.anadolusigorta.campaignmanagement.domain.saletransaction.port;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.CampaignFindOperation;

import java.util.List;

public interface CampaignTransactionalSaleRequestRepository {

	void saveFindCampaignsProcessRequest(CampaignFindOperation campaignFindOperation);

	void saveCheckProcessRequest(CheckSaleStatus checkSaleStatus);

	void saveNotifyProcessRequest(CreateNotifySaleCampaign createNotifySaleCampaign);
}
