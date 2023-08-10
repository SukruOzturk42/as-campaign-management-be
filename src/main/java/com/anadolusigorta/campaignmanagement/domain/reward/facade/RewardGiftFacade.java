package com.anadolusigorta.campaignmanagement.domain.reward.facade;

import com.anadolusigorta.campaignmanagement.domain.reward.RewardBaseModel;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
import com.anadolusigorta.campaignmanagement.domain.reward.model.*;
import com.anadolusigorta.campaignmanagement.domain.reward.port.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardGiftFacade {
    private final RewardGiftDeliveryStartTypeRepository rewardGiftDeliveryStartTypeRepository;
    private final RewardGiftDeliveryTypeRepository rewardGiftDeliveryTypeRepository;
    private final RewardGiftKindRepository rewardGiftKindRepository;
    private final RewardGiftPaymentTypeRepository rewardGiftPaymentTypeRepository;
    private final RewardGiftTypeRepository rewardGiftTypeRepository;
    private final RewardCompanyInformationRepository rewardCompanyInformationRepository;
    private final RewardGiftProductRepository rewardGiftProductRepository;
    private final RewardGiftTicketTypeRepository rewardGiftTicketTypeRepository;
    private final RewardGiftSendMethodTypeRepository rewardGiftSendMethodTypeRepository;
    private final RewardRoleRepository rewardRoleRepository;



    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardGiftDeliveryStartType> getRewardGiftDeliveryStartTypes(Long campaignTypeId) {
        return rewardGiftDeliveryStartTypeRepository.findAllByCampaignTypeId(campaignTypeId);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardGiftDeliveryType> getRewardGiftDeliveryTypes() {
        return rewardGiftDeliveryTypeRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardGiftKind> getRewardGiftKinds() {
        return rewardGiftKindRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardGiftPaymentType> getRewardGiftPaymentTypes() {
        return rewardGiftPaymentTypeRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardGiftType> getRewardTypes() {
        return rewardGiftTypeRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardCompanyInformation> getRewardCompanyInformation() {
        return rewardCompanyInformationRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public RewardCompanyInformation saveRewardCompanyInformation(RewardCompanyInformation rewardCompanyInformation) {
        return rewardCompanyInformationRepository.save(rewardCompanyInformation);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardGiftProduct> getRewardGiftProducts() {
        return rewardGiftProductRepository.findAll();
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardGiftSendMethodType> getRewardGiftMethodTypes() {
        return rewardGiftSendMethodTypeRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public RewardGiftProduct createRewardGiftProduct(RewardGiftProduct rewardGiftProduct){
              return rewardGiftProductRepository.saveRewardGiftProduct(rewardGiftProduct);
    }


    public List<RewardGiftTicketType> getRewardTicketTypes(){
            List<RewardGiftTicketType> giftList = rewardGiftTicketTypeRepository.findAll();
       var sortedList = giftList.stream().sorted(Comparator.comparing(RewardGiftTicketType::getId)).
                collect(Collectors.toList());

        return sortedList;
    }

    public RewardGiftTicketType saveRewardGiftTicketType(RewardGiftTicketType rewardGiftTicketType) {
        return rewardGiftTicketTypeRepository.save(rewardGiftTicketType);
    }



    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RewardRole> getRewardRoles() {
        return rewardRoleRepository.findAll();
    }


}
