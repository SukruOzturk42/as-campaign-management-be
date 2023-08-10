package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.sale.model.CheckStatusFlow;
import com.anadolusigorta.campaignmanagement.domain.sale.port.SaleCheckFlowRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignflow.checkcodestatus.repository.CheckCodeStatusFlowGroupJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SaleCheckFlowRepositoryJpaAdapter implements SaleCheckFlowRepository {

    private final CheckCodeStatusFlowGroupJpaRepository checkCodeStatusFlowGroupJpaRepository;
    @Override
    public List<CheckStatusFlow> findAllByCampaignTypeAndRequestTypeOrderByRunOrder(String campaignType, String requestType) {
        return checkCodeStatusFlowGroupJpaRepository.findAllByCampaignTypeAndRequestTypeOrderByRunOrder(campaignType, requestType)
                .stream()
                .map(item->CheckStatusFlow.of(item.getCheckCodeStatusFlowEntity().getParametersTobeChecked()))
                .toList();
    }
}
