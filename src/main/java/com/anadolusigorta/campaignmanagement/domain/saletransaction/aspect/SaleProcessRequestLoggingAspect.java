/* dks20165 created on 5.01.2022 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.aspect */

package com.anadolusigorta.campaignmanagement.domain.saletransaction.aspect;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactFacade;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.sale.facade.SaleCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.facade.SaleTransactionDetailFacade;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.CampaignFindOperation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionDetail;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.port.CampaignTransactionalSaleRequestRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SaleProcessRequestLoggingAspect {

	private final CampaignTransactionalSaleRequestRepository campaignTransactionalSaleRequestRepository;
	private final SaleTransactionDetailFacade saleTransactionDetailFacade;
	private final SaleCampaignFacade saleCampaignFacade;

	@After(value = "execution(* com.anadolusigorta.campaignmanagement.domain.saletransaction.facade.CampaignTransactionalSaleProcessFacade.handleFindCampaignsProcess(..)) " +
			"&& args(campaignFindOperation)", argNames = "campaignFindOperation")
	public void saveFindRequest(CampaignFindOperation campaignFindOperation){
			campaignTransactionalSaleRequestRepository.saveFindCampaignsProcessRequest(campaignFindOperation);

	}

	@Before(value = "execution(* com.anadolusigorta.campaignmanagement.domain.saletransaction.facade.CampaignTransactionalSaleProcessFacade.handleCheckProcess(..)) " +
			"&& args(checkSaleStatus)", argNames = "checkSaleStatus")
	public void saveCheckSaleRequest(CheckSaleStatus checkSaleStatus){

			campaignTransactionalSaleRequestRepository.saveCheckProcessRequest(checkSaleStatus);
			if(checkSaleStatus.getInsuredCheckStatus()!=null){
				checkSaleStatus.getInsuredCheckStatus().forEach(this::handleCheck);
			}else{
				handleCheck(checkSaleStatus);
			}




	}

	@Before(value = "execution(* com.anadolusigorta.campaignmanagement.domain.saletransaction.facade.CampaignTransactionalSaleProcessFacade.handleNotifyProcess(..)) " +
			"&& args(createNotifySaleCampaign)", argNames = "createNotifySaleCampaign")
	public void saveNotifySaleRequest(CreateNotifySaleCampaign createNotifySaleCampaign){



			if(createNotifySaleCampaign.getInsured()!=null){

				createNotifySaleCampaign.getInsured().forEach(this::handleNotify);

			}else{
				handleNotify(createNotifySaleCampaign);
			}

		campaignTransactionalSaleRequestRepository.saveNotifyProcessRequest(createNotifySaleCampaign);



	}

	private void handleCheck(CheckSaleStatus checkSaleStatus) {

		var transactionDetail = saleTransactionDetailFacade
				.getByTransactionId(checkSaleStatus.getTransactionId(),checkSaleStatus.getContactNumber());
		var transactionDetailRequest = SaleTransactionDetail.builder()
				.campaignId(checkSaleStatus.getCampaignId())
				.ruleGroupId(checkSaleStatus.getRuleGroupId()).transactionId(checkSaleStatus.getTransactionId())
				.contactNo(checkSaleStatus.getContactNumber()).checkOperationType(checkSaleStatus.getOperationType())
				.campaignCode(checkSaleStatus.getCampaignCode()).saleTransactionType(SaleTransactionType.CHECK_CODE)
				.build();
		var isCorrectTransaction = saleTransactionDetailFacade
				.checkSaleTransactionByTransactionType(transactionDetail,
						transactionDetailRequest);
		if (isCorrectTransaction.equals(Boolean.TRUE)) {
			transactionDetail.setCampaignId(checkSaleStatus.getCampaignId());
			transactionDetail.setRuleGroupId(checkSaleStatus.getRuleGroupId());
			transactionDetail.setCheckOperationType(checkSaleStatus.getOperationType());
			transactionDetail.setSaleTransactionType(SaleTransactionType.CHECK_CODE);
			transactionDetail.setCampaignCode(checkSaleStatus.getCampaignCode());
			saleTransactionDetailFacade.save(transactionDetail);

		} else {
			throw new CampaignManagementException("invalid.transaction",checkSaleStatus.getTransactionId()
					,checkSaleStatus.getContactNumber());
		}
	}

	private void handleNotify(CreateNotifySaleCampaign createNotifySaleCampaign) {

		var transactionDetail = saleTransactionDetailFacade
				.getByTransactionId(createNotifySaleCampaign.getTransactionId()
						,createNotifySaleCampaign.getContactNumber());

		log.info(String.format("transaction detail record : %s", new Gson().toJson(transactionDetail)));

		var transactionDetailRequest = SaleTransactionDetail.builder()
				.campaignId(createNotifySaleCampaign.getCampaignId())
				.ruleGroupId(createNotifySaleCampaign.getRuleGroupId())
				.transactionId(createNotifySaleCampaign.getTransactionId())
				.contactNo(createNotifySaleCampaign.getContactNumber())
				.campaignCode(createNotifySaleCampaign.getCampaignCode())
				.saleTransactionType(SaleTransactionType.NOTIFY)
				.saleOperation(createNotifySaleCampaign.getRequestType()).build();
		var isCorrectTransaction = saleTransactionDetailFacade
				.checkSaleTransactionByTransactionType(transactionDetail,
						transactionDetailRequest);
		if (isCorrectTransaction.equals(Boolean.TRUE)) {
			transactionDetail.setSaleOperation(createNotifySaleCampaign.getRequestType());
			transactionDetail.setSaleTransactionType(SaleTransactionType.NOTIFY);
			saleTransactionDetailFacade.save(transactionDetail);

		} else {
			log.info(String.format("transaction detail request : %s", new Gson().toJson(createNotifySaleCampaign)));

			throw new CampaignManagementException("invalid.transaction",createNotifySaleCampaign.getTransactionId()
					,createNotifySaleCampaign.getContactNumber());
		}
	}
}
