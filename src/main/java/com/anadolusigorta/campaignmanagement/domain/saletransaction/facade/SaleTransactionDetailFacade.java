package com.anadolusigorta.campaignmanagement.domain.saletransaction.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.AvailableCampaign;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionCheckOperationType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionDetail;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.port.SaleTransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleTransactionDetailFacade {


    private final SaleTransactionDetailRepository saleTransactionDetailRepository;

    public SaleTransactionDetail getByTransactionId(String transactionId,String contactNo){
        return saleTransactionDetailRepository.findByTransactionId(transactionId,contactNo);
    }

    public void saveInitialTransaction(String transactionId,String contactNo, Map<String, List<AvailableCampaign>> availableCampaigns){

        var campaigns=availableCampaigns.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        var transactionData=campaigns.stream()
                .collect(Collectors
                        .toMap(item->item.getCampaignInformation().getCampaignId()
                                ,item->item.getRuleGroup().getRuleGroupId()));

        var transactionDetail=SaleTransactionDetail.builder()
                .transactionId(transactionId)
                .contactNo(contactNo)
                .transactionData(transactionData)
                .saleTransactionType(SaleTransactionType.CHECK_CODE)
                .build();

          saleTransactionDetailRepository.save(transactionDetail);

    }
    public SaleTransactionDetail save(SaleTransactionDetail saleTransactionDetail){
        return saleTransactionDetailRepository.save(saleTransactionDetail);

    }

    public Boolean checkSaleTransactionByTransactionType(SaleTransactionDetail transaction,
            SaleTransactionDetail saleTransactionDetailRequest){
        try {
            switch (saleTransactionDetailRequest.getSaleTransactionType()) {
                case CHECK_CODE:
                    return transaction.isCorrectSaleTransaction(saleTransactionDetailRequest);
                case NOTIFY:
                    return transaction.isCorrectSaleTransaction(saleTransactionDetailRequest) &&
                            transaction.isTransactionOperationEnable(saleTransactionDetailRequest)
                            && transaction.isCorrectCode(saleTransactionDetailRequest);
                default:
                    return false;
            }
        } catch (Exception e) {
            throw new CampaignManagementException("invalid.transaction",saleTransactionDetailRequest.getTransactionId()
                    ,saleTransactionDetailRequest.getContactNo());
        }
    }



}
