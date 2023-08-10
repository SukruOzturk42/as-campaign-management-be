package com.anadolusigorta.campaignmanagement.domain.sale.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.port.DiscountCodeInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.*;
import com.anadolusigorta.campaignmanagement.domain.sale.port.SaleCampaignCriteriaRepository;
import com.anadolusigorta.campaignmanagement.domain.sale.port.SaleCampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.CmTaskFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleCampaignFacade {

    private final SaleCampaignRepository saleCampaignRepository;

    private final SaleCampaignCriteriaRepository saleCampaignCriteriaRepository;

    private final DiscountCodeInformationRepository discountCodeInformationRepository;
    private final SaleCheckFacade saleCheckFacade;

    public SaleInformation saveNotifySaleCampaign(CreateNotifySaleCampaign createNotifySaleCampaign) {


        log.info(String.format("save sale information transactionId: %s and contactNo: %s",
                createNotifySaleCampaign.getTransactionId(), createNotifySaleCampaign.getContactNumber()));
        CompletableFuture.supplyAsync(() -> {
            saleCampaignRepository.saveCampaignSaleInformation(createNotifySaleCampaign);
             return null;
        });
        return SaleInformation
                .builder()
                .transactionId(createNotifySaleCampaign.getTransactionId())
                .contactNumber(createNotifySaleCampaign.getContactNumber())
                .build();

    }

    public boolean checkNotifySaleCampaignCodes(CreateNotifySaleCampaign createNotifySaleCampaign) {

        return saleCampaignRepository.checkCampaignSaleInformationCodes(createNotifySaleCampaign);

    }

    public List<SaleCampaignInformation> findSaleCampaignInformation(){

        return saleCampaignRepository.getSaleCampaignInformation();
    }

    public List<SaleReport> getSaleInformation(SaleReportCriteria saleCampaignInformationCriteria){

        return saleCampaignCriteriaRepository.findSaleCampaignInformation(saleCampaignInformationCriteria);
    }


    public PageContent<SaleReport> getSaleInformation(SaleReportCriteria saleCampaignInformationCriteria,
                                                      Pageable pageable) {
        return saleCampaignCriteriaRepository.findSaleCampaigns(saleCampaignInformationCriteria,pageable);
    }

    public CheckSaleStatus checkSaleOperation(CheckSaleStatus checkSaleStatus){


        log.info(String.format("check code facade transactionId: %s and contactNo: %s",
                checkSaleStatus.getTransactionId(), checkSaleStatus.getContactNumber()));

        return saleCheckFacade.checkSaleStatus(checkSaleStatus);

    }

    public boolean checkSaleCodes(CheckSaleStatus checkSaleStatus){

        return discountCodeInformationRepository.checkSaleStatusCodes(checkSaleStatus);

    }

    public List<SaleRewardGift> getSuitableSaleRewardGiftsForSendOperation() {
        return saleCampaignRepository.findAvailableSaleRewardGiftsForSendOperation()
                .stream()
                .filter(item->item.getCampaignRewardGift().getCampaignRewardGift().getRewardGiftSendMethodType()!=null
                          && item.getSaleInformation().getSoldPolicyDetail()!=null
                         && item.getSaleInformation().getSoldPolicyDetail().getPolicyNumber()!=null)
                .collect(Collectors.toList());
    }

    public List<SaleRewardGift> getSuitableSaleRewardGiftsForReSendOperationByCampaignInformationId(Long campaignInfoId) {
        return saleCampaignRepository.findAvailableSaleRewardGiftsForReSendOperationByCampaignInformationId(campaignInfoId)
                .stream()
                .filter(item->item.getCampaignRewardGift().getCampaignRewardGift().getRewardGiftSendMethodType()!=null
                        && item.getSaleInformation().getSoldPolicyDetail()!=null
                        && item.getSaleInformation().getSoldPolicyDetail().getPolicyNumber()!=null)
                .collect(Collectors.toList());
    }

    public void terminateExpiredRewardSendOperation() {
       saleCampaignRepository.terminateExpiredRewardSendOperation();
    }

    public void checkRewardSendStatus() {
        saleCampaignRepository.checkRewardSendOperation();
    }

    public void saveSaleRewardGift(SaleRewardGift saleRewardGift) {
        saleCampaignRepository.saveSaleRewardGift(saleRewardGift);
    }

    public void saveSaleRewardGiftTicket(SaleRewardGift saleRewardGift, GiftCodeInformation giftCodeInformation) {
        saleCampaignRepository.saveSaleRewardGift(saleRewardGift, giftCodeInformation);
    }

    public SaleInformationSummary getSaleCampaignSummary(Long campaignId){

        return saleCampaignRepository.getSaleCampaignSummary(campaignId);
    }

    public Boolean triggerSaleRewardSendOperation(Long campaignId){
        return saleCampaignRepository.triggerSaleRewardSendOperation(campaignId);
    }

    public Boolean triggerSaleRewardSendOperation(Long campaignId,Boolean triggerValue){
        return saleCampaignRepository.triggerSaleRewardSendOperation(campaignId,triggerValue);
    }

    public Boolean startSaleRewardSendOperation(Long campaignId){

        return saleCampaignRepository.startSaleRewardSendOperation(campaignId);

    }

    public Boolean stopSaleRewardSendOperation(Long campaignId){

        return saleCampaignRepository.stopSaleRewardSendOperation(campaignId);


    }

    public Boolean stopSaleCampaignRewardSendOperation(){
        return saleCampaignRepository.stopSaleCampaignRewardSendOperation();
    }

    public Boolean startSaleCampaignRewardSendOperation(){
        return saleCampaignRepository.startCampaignSaleRewardSendOperation();
    }

    public RemoveSaleReward removeSale(RemoveSaleReward removeSaleReward){
         return saleCampaignRepository.removeContactSaleReward(removeSaleReward);
    }

}
