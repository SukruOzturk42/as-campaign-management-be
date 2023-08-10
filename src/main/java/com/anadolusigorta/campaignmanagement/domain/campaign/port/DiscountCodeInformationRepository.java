package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateDiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformationCriteria;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeInformationRepository {


    Optional<DiscountCode> findDiscountCodeByDiscountCodeInformation(Long discountInformationId, String code);

    List<DiscountCode> getAllDiscountCodeByDiscountCodeInformation(Long discountInformationId);

    List<DiscountCode> getAllDiscountCodes();

    PageContent<DiscountCode> getAllDiscountCodes(String codeGroupName,String contactNumber,
                                                  String code,String codeStatus, Pageable pageable);

    PageContent<DiscountCodeInformation> getDiscountCodeInformationList(DiscountCodeInformationCriteria discountCodeInformationCriteria, Pageable pageable);

    PageContent<DiscountCodeInformation> getDiscountCodeInformationList(String codeGroupName,String contactNumber,Pageable pageable);

    List<DiscountCodeInformation> getAllActiveDiscountCodeInformation();

    DiscountCodeInformation saveDiscountCodeInformation(CreateDiscountCodeInformation createDiscountCodeInformation);


    boolean checkSaleStatusCodes(CheckSaleStatus checkSaleStatus);

    void saveDiscountCodes(CreateDiscountCodeInformation createDiscountCodeInformation,
                           DiscountCodeInformation discountCodeInformation);

    void createDiscountCodeInformationDetail(CreateDiscountCodeInformation createDiscountCodeInformation,
                                                    DiscountCodeInformation discountCodeInformation, String userFullName);

    List<UsedCodeGroupInformation> getCampaignListByUsedDiscountCodeInformationId(Long discountCodeInformationId);
}
