/* dks20165 created on 12.12.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.saletransaction.facade */

package com.anadolusigorta.campaignmanagement.domain.saletransaction.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.AvailableCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactFacade;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.sale.facade.SaleCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.CampaignFindOperation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionDetail;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.port.CampaignTransactionalSaleRequestRepository;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.CmTaskFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignTransactionalSaleProcessFacade implements CampaignTransactionalSaleProcess {

	private final ContactFacade contactFacade;
	private final SaleTransactionDetailFacade saleTransactionDetailFacade;
	private final SaleCampaignFacade saleCampaignFacade;
	private final CampaignFacade campaignFacade;

	/**
	 * <p>
	 * first stage of campaign sale process that is find salable campaigns.
	 * 
	 *transactionId of campaignFindOperation is used to control sales processes.
	 * @return salable campaign
	 */
	@Override
	public Map<String, List<AvailableCampaign>> handleFindCampaignsProcess(CampaignFindOperation campaignFindOperation) {

		log.info(String.format(
				"Find Campaigns Request With Transaction ID: %s ----> Contact Number In Find Service: %s ----> %s",
				campaignFindOperation.getTransactionId(), campaignFindOperation.getContactNumber(),
				campaignFindOperation.getContactCampaignInformationList().toString()));


		var salableCampaigns = contactFacade
				.getAvailableCampaignForContact(campaignFindOperation.getContactNumber(),
				campaignFindOperation.getContactCampaignInformationList());

		saleTransactionDetailFacade.saveInitialTransaction(campaignFindOperation.getTransactionId(), campaignFindOperation.getContactNumber(),
				salableCampaigns);

		return salableCampaigns;
	}

	/**
	 * <p>
	 * The second stage of the sales process. The campaign status is checked before
	 * the sales process.
	 * 
	 * @param checkSaleStatus transactionId is used to control sales processes.
	 * @return campaign status
	 */
	@Override
	public CheckSaleStatus handleCheckProcess(CheckSaleStatus checkSaleStatus) {

		if(checkSaleStatus.getInsuredCheckStatus()!=null){
			var codeChecks=saleCampaignFacade.checkSaleCodes(checkSaleStatus);
			if(!codeChecks){
				throw new CampaignManagementException("multiple.single.code.error");
			}
			return CheckSaleStatus.builder()
					.insuredCheckStatus(checkSaleStatus.getInsuredCheckStatus()
									.stream()
									.filter(CheckSaleStatus::getIsActiveContact)
									.map(this::handleCheck)
									.collect(Collectors.toList()))
					.build();
		}
		return handleCheck(checkSaleStatus);

	}

	@Override
	public CheckSaleStatus handleCheckCampaignProcess(CheckSaleStatus checkSaleStatus) {

		if(checkSaleStatus.getInsuredCheckStatus()!=null){
			var codeChecks=saleCampaignFacade.checkSaleCodes(checkSaleStatus);
			if(!codeChecks){
				throw new CampaignManagementException("multiple.single.code.error");
			}
			return CheckSaleStatus.builder()
					.insuredCheckStatus(checkSaleStatus.getInsuredCheckStatus()
							.stream()
							.filter(CheckSaleStatus::getIsActiveContact)
							.map(this::handleCheck)
							.collect(Collectors.toList()))
					.build();
		}
		return handleCheck(checkSaleStatus);

	}


	/**
	 * <p>
	 * The third stage of the sales process. Notify the campaign sale.
	 * 
	 * @param createNotifySaleCampaign of transactionId is used to control sales processes.
	 * @return sale information.
	 */
	@Override
	public SaleInformation handleNotifyProcess(CreateNotifySaleCampaign createNotifySaleCampaign) {

		if(createNotifySaleCampaign.getInsured()!=null){
			var codeChecks=saleCampaignFacade.checkNotifySaleCampaignCodes(createNotifySaleCampaign);
			if(!codeChecks){
				throw new CampaignManagementException("multiple.single.code.error");
			}
            return   SaleInformation.builder()
					 .insuredSaleInformation(createNotifySaleCampaign.getInsured().stream()
							 .map(this::handleNotify)
							 .collect(Collectors.toList()))
					 .build();
		}
		return handleNotify(createNotifySaleCampaign);
	}

	private CheckSaleStatus handleCheck(CheckSaleStatus checkSaleStatus) {
		log.info(String.format("Check Sale Request With Transaction ID: %s ----> %s", checkSaleStatus.getTransactionId(),
				checkSaleStatus));

		return saleCampaignFacade.checkSaleOperation(checkSaleStatus);

	}


	private SaleInformation handleNotify(CreateNotifySaleCampaign createNotifySaleCampaign) {
		log.info(String.format("Handle Notify Request With Transaction ID: %s ----> %s",
				createNotifySaleCampaign.getTransactionId(),
				createNotifySaleCampaign));

		var saleInfo=saleCampaignFacade.saveNotifySaleCampaign(createNotifySaleCampaign);
		saleInfo.setTransactionId(createNotifySaleCampaign.getTransactionId());
		return saleInfo;
	}
}
