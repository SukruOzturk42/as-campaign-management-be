package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.GiftCodeInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCodeInformationFacade {

    private final GiftCodeInformationRepository giftCodeInformationRepository;

    public List<GiftCodeInformation> getAllGiftCodeInformation() {
        return giftCodeInformationRepository.getAllGiftCodeInformation();
    }

    public PageContent<GiftCodeInformation> getAllPageableGiftCodeInformation(Pageable pageable) {
        return giftCodeInformationRepository.getPageableGiftCodeInformationList(pageable);
    }

    public GiftCodeInformation saveGiftCodeInformation(CreateGiftCodeInformation createGiftCodeInformation) {
        return giftCodeInformationRepository.saveGiftCodeInformation(createGiftCodeInformation);
    }

    public GiftCode getAvailableGiftCode(GiftCodeInformation giftCodeInformation) {
        return giftCodeInformationRepository.getAvailableGiftCode(giftCodeInformation);
    }

    public List<GiftCode> getAvailableGiftCodesByLimit(GiftCodeInformation giftCodeInformation, Long limit) {
        return giftCodeInformationRepository.getAvailableGiftCodeByLimit(giftCodeInformation,limit);
    }

    public void setCustomerToGiftCode(GiftCode giftCode, String contactNumber) {
        giftCodeInformationRepository.setCustomerToGiftCode(giftCode, contactNumber);
    }

    public void assignCodesToContact(String contactNumber,Long codeInformationId,List<String> giftCodes) {
        giftCodeInformationRepository.assignCodesToContact(contactNumber,codeInformationId, giftCodes);
    }

    public List<UsedCodeGroupInformation> getCampaignsByUsedGiftCodeInformationId(Long giftCodeInformationId) {
        return giftCodeInformationRepository.getCampaignListByUsedGiftCodeInformationId(giftCodeInformationId);
    }

    public List<GiftCode> getAllGiftCodeByGiftCodeInformationId(Long giftCodeInformationId) {
        return giftCodeInformationRepository.getAllGiftCodeByGiftCodeInformationId(giftCodeInformationId);
    }

    public List<GiftCode> getAllGiftCodes() {
        return giftCodeInformationRepository.getAllGiftCodes();
    }

    public List<GiftCode> getRewardAGiftCodes(Long rewardGiftId) {
        return giftCodeInformationRepository.findRewardGiftCodes(rewardGiftId);
    }

    public PageContent<GiftCode> getAllGiftCodes(String codeGroupName,String contactNumber,
                                                 String code,String codeStatus, Pageable pageable) {
        return giftCodeInformationRepository.getAllGiftCodes(codeGroupName,contactNumber,code,codeStatus,pageable);
    }

}
