package com.anadolusigorta.campaignmanagement.domain.sale.port;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.sale.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SaleCampaignRepository {

	CompletableFuture<SaleInformation> saveCampaignSaleInformation(CreateNotifySaleCampaign createNotifySaleCampaign);

	boolean checkCampaignSaleInformationCodes(CreateNotifySaleCampaign createNotifySaleCampaign);

	List<SaleRewardGift> findAvailableSaleRewardGiftsForSendOperation();

	List<SaleRewardGift> findAvailableSaleRewardGiftsForReSendOperationByCampaignInformationId(Long campaignInformationId);

	void terminateExpiredRewardSendOperation();

	void checkRewardSendOperation();

	void saveSaleRewardGift(SaleRewardGift saleRewardGift);

	void saveSaleRewardGift(SaleRewardGift saleRewardGift, GiftCodeInformation giftCodeInformation);

	List<SaleCampaignInformation> getSaleCampaignInformation();

	SaleInformationSummary getSaleCampaignSummary(Long campaignId);

	Boolean triggerSaleRewardSendOperation(Long campaignId);

	Boolean triggerSaleRewardSendOperation(Long campaignId, Boolean triggerValue);

	Boolean startSaleRewardSendOperation(Long campaignId);

	Boolean stopSaleRewardSendOperation(Long campaignId);

	Boolean stopSaleCampaignRewardSendOperation();

	Boolean startCampaignSaleRewardSendOperation();

	RemoveSaleReward removeContactSaleReward(RemoveSaleReward removeSaleReward);


	SaleRewardGift  findBySaleRewardId(Long id);

    int contactSaleCountInCampaign(String contactNo,Long campaignId);

	Boolean hasContactOldProposal(String contactNo,String proposalNumber,String revisionNumber);




}
