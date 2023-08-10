package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftProduct;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftProductRepository;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardGiftProductEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftProductJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftProductRepositoryJpaAdapter implements RewardGiftProductRepository {

    private final GiftProductJpaRepository giftProductJpaRepository;

    @Override
    public List<RewardGiftProduct> findAll() {
        return giftProductJpaRepository.findAll()
                .stream()
                .map(RewardGiftProductEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public RewardGiftProduct saveRewardGiftProduct(RewardGiftProduct rewardGiftProduct) {
        var newProduct= new RewardGiftProductEntity();
        newProduct.setName(rewardGiftProduct.getName());
        return giftProductJpaRepository.save(newProduct).toModel();
    }
}
