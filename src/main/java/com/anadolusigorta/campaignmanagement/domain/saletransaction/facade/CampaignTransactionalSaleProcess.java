/* dks20165 created on 12.12.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.saletransaction.facade */

package com.anadolusigorta.campaignmanagement.domain.saletransaction.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.AvailableCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.CampaignFindOperation;

import java.util.List;
import java.util.Map;

public interface CampaignTransactionalSaleProcess {

	Map<String, List<AvailableCampaign>> handleFindCampaignsProcess(CampaignFindOperation campaignFindOperation);

	CheckSaleStatus handleCheckProcess(CheckSaleStatus checkSaleStatus);

	CheckSaleStatus handleCheckCampaignProcess(CheckSaleStatus checkSaleStatus);

	SaleInformation handleNotifyProcess(CreateNotifySaleCampaign createNotifySaleCampaign);
}
