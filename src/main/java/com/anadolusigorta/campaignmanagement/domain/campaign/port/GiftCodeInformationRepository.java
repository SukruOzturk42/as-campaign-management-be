package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GiftCodeInformationRepository {

    List<GiftCodeInformation> getAllGiftCodeInformation();

    PageContent<GiftCodeInformation> getPageableGiftCodeInformationList(Pageable pageable);

    GiftCodeInformation saveGiftCodeInformation(CreateGiftCodeInformation createGiftCodeInformation);

    GiftCode getAvailableGiftCode(GiftCodeInformation giftCodeInformation);

    List<GiftCode> getAvailableGiftCodeByLimit(GiftCodeInformation giftCodeInformation,Long count);

    void setCustomerToGiftCode(GiftCode giftCode, String contactNumber);

    void assignCodesToContact(String contactNumber,Long codeInformationId,List<String> giftCodes);

    List<UsedCodeGroupInformation> getCampaignListByUsedGiftCodeInformationId(Long giftCodeInformationId);

    List<GiftCode> getAllGiftCodeByGiftCodeInformationId(Long giftCodeInformationId);

    List<GiftCode> getAllGiftCodes();

    List<GiftCode> findRewardGiftCodes(Long rewardGiftId);

    PageContent<GiftCode> getAllGiftCodes(String codeGroupName,String contactNumber,
                                          String code,String codeStatus, Pageable pageable);
}
