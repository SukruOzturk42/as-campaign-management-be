/* dks20165 created on 14.12.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.sale.model.CreateNotifySaleCampaign;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.CampaignFindOperation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.port.CampaignTransactionalSaleRequestRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.CheckCodeRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.FindSaleCampaignsRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.CheckCodeRequestJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleCampaignRequestJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleFindCampaignsRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CampaignTransactionalSaleRequestRepositoryJpaAdapter implements
		CampaignTransactionalSaleRequestRepository {


	private final CheckCodeRequestJpaRepository checkCodeRequestJpaRepository;
	private final SaleCampaignRequestJpaRepository saleCampaignRequestJpaRepository;
	private final SaleFindCampaignsRequestJpaRepository saleFindCampaignsRequestJpaRepository;

	@Override
	public void saveFindCampaignsProcessRequest(CampaignFindOperation campaignFindOperation) {
		var entity= FindSaleCampaignsRequestEntity.fromModel(campaignFindOperation);
		saleFindCampaignsRequestJpaRepository.save(entity);
	}

	@Override
	public void saveCheckProcessRequest(CheckSaleStatus checkSaleStatus) {
		var entity=CheckCodeRequestEntity.fromModel(checkSaleStatus);
		 checkCodeRequestJpaRepository.save(entity);

	}

	@Override
	public void saveNotifyProcessRequest(CreateNotifySaleCampaign createNotifySaleCampaign) {

		if(createNotifySaleCampaign.getInsured()!=null){
			createNotifySaleCampaign.getInsured().forEach(this::saveNotify);
		}
		else{

			saveNotify(createNotifySaleCampaign);
		}



	}

	private void saveNotify(CreateNotifySaleCampaign createNotifySaleCampaign) {
		var requestType= createNotifySaleCampaign.getRequestType();

		if(requestType.equals(SaleTransactionOperationType.POLICY)){

			var transactionRecord=saleCampaignRequestJpaRepository
					.findByTransactionalIdAndRequestType(createNotifySaleCampaign.getTransactionId(),requestType);

			if(!transactionRecord.isEmpty()){
				log.info(String.format("Same transactionId error with POLICY request type transactionId:%s", createNotifySaleCampaign.getTransactionId()));

				throw new CampaignManagementException("invalid.transaction", createNotifySaleCampaign.getTransactionId()
						, createNotifySaleCampaign.getContactNumber());
			}

		}
		var entity= SaleCampaignRequestEntity.fromModel(createNotifySaleCampaign);
		saleCampaignRequestJpaRepository.save(entity);
	}
}
