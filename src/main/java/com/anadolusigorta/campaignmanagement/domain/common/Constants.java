/* odeon_sukruo created on 2.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.common */

package com.anadolusigorta.campaignmanagement.domain.common;

public class Constants {

	public static final String TAGS_DELIMITER = ",";
	public static final String MESSAGE_DELIMITER = ";";
	public static final String ATTRIBUTE_VALUE_DELIMITER = ",";
	public static final Integer DEFAULT_CUSTOMER_LIMIT_SIZE = 1;
	public static final String CLOSED_CAMPAIGN_ACTION_NAME = "Kapalı";
	public static final String VERSION_START_DATE_PENDING = "Sürüm başlangıç tarihi bekleniyor.";

	public static final Integer DEFAULT_PARTICIPATION_ORDER = 1;
	public static final Integer DEFAULT_PARTICIPATION_NUMBER = 1;
	public static final String CAMPAIGN_PARTICIPATION_TYPE_NAME = "participation";
	public static final String YES_PARAM_VALUE = "1";
	public static final String NO_PARAM_VALUE = "0";



	public static  final String MANDATORY_PARAMETER_KEY="policyType";
	public static final String DEFAULT_SALE_CHANNEL_TYPE = "2000005";
	public static final String OWNER_PRODUCT_PARAMETER_NAME ="ownerProduct" ;
	public static final String CONTACT_ROLE_PARAMETER_NAME ="policyContactRole";
	public static final String HEALTH_PACKAGE_PARAMETER_NAME ="healthPackage";
	public static final String VIN_NUMBERS_PARAMETER_NAME ="vinNumbers";


	public static final String IS_SAME_VEHICLE_PARAMETER_NAME ="isSameVehicle";
	public static final String CUSTOMER_NO_ATTRIBUTE_NAME = "customerNo";
	public static final String BIRTH_DATE_PARAM_KEY ="birthdate" ;
	public static final String CONTACT_AGE_PARAM_KEY ="contactAge" ;

	public static final String AGENCY_CODE_RANGE_PARAM_KEY ="agentCodeRange" ;

	public static final String AGENCY_CODE_PARAM_KEY ="agentCode" ;

	public static final String CUSTOMER_PORTFOLIO_START_DATE ="customerPortfolioStartDate" ;
	public static final String IS_NEW_CONTACT ="isNewContact" ;
	public static final String CUSTOMER_NEW_PRODUCT ="customerNewProduct" ;
	public static final String COVER_CODE = "coverCode";
	public static final String RELATED_TYPE = "relatedType";
	public static final String AFFINITY_CONTROL = "affinityControl";
	public static final String IS_ISBANK_FACE_TO_FACE_BANKING_CAMPAIGN = "isIsBankFaceToFaceBankingCampaign";

	public static final String CONTACT_ENTITY_TYPE_LEGAL_EXT_CODE_TR = "TTR";
	public static final String CONTACT_ENTITY_TYPE_LEGAL_EXT_CODE_F = "TYR";
	public static final String CONTACT_ENTITY_TYPE_PERSON_EXT_CODE_TR = "OTR";
	public static final String CONTACT_ENTITY_TYPE_PERSON_EXT_CODE_F = "OTR";


	public static final String RELATED_COOPERATION ="relatedCorporation";
	public static final String COOPERATION_CAMPAIGN_TYPE ="cooperation";
	public static final String RATIO_DISCOUNT_KIND ="ratio";



	public static final String CUSTOMER_CAMPAIGN_TYPE_NAME ="sale" ;

	public static final String PARAMETER = "Parametre";
	public static final String RULE = "Kural";

	public static final String DISCOUNT_CODE_CAMPAIGN = "discountCodeCampaign";
	public static final String EXT_CODE_CAMPAIGN = "extCodeCampaign";
	public static final String CROSS_SALE_CAMPAIGN = "crossSaleCampaign";

	public static final String IS_BANK_CAMPAIGN = "isBankCampaign";
	public static final String GIFT_OR_NO_DISCOUNT_CAMPAIGN = "giftOrNoDiscountCampaign";
	public static final String GIFT_TICKET_TYPE="giftTicket";
	public static final String GIFT_TYPE="gift";



	public static final String DATE_TIME_PATTERN="yyyy-MM-dd HH:mm";
	public static final String DATE_PATTERN="yyyy-MM-dd";
	public static final String DATE_TIME_PATTERN_SECONDS = "uuuu-MM-dd'T'HH:mm:ss.SSSSS";

	public static final String EMPTY_STRING="";

	public static final String LANGUAGE_HEADER_NAME = "lang";
	public static final String DEFAULT_LANGUAGE_NAME = "tr_TR";

	public static final String YES_TR = "Evet";
	public static final String NO_TR = "Hayır";
	public static final String UNUSED_CODE_TR = "Uygun";
	public static final String PROPOSED_CODE_TR = "Atanmış";
	public static final String USED_CODE_TR = "Kullanılmış";
	public static final String APPROVED = "Onaylandı";

	public static final String RULE_STATE_NAME = "state";


	public static final String REGIONAL_USER_NAME="RegionalUser";
	public static final String AGENCY_USER="AgencyUser";
	public static final String BSM_USER="BSM";
	public static final String MSU_USER="MSU";


	public static final String PENDING_NOTIFY_STATUS="0";
	public static final String CAMPUS_NOTIFY_STATUS="10";
	public static final String CAMPUS_NOTIFY_CANCEL_STATUS="13";

	public static final String SENT_NOTIFY_STATUS="1";
	public static final String CANCELLED_NOTIFY_STATUS="-2";
	public static final String ERROR_NOTIFY_STATUS="-1";
	public static final String EXPIRED_NOTIFY_STATUS="11";

	public static final String DELETE_REWARD_STATUS="12";
	public static final String DELIVERED_NOTIFY_STATUS="2";

	public static final String DELIVERED_NOTIFY_STATUS_NAME="DELIVERED";
	public static final String PENDING_NOTIFY_STATUS_DESC="Gönderim Bekliyor";

	public static final String TASK_STATE_OPEN = "Open";
	public static final String TASK_STATE_ON_PROGRESS = "OnProgress";
	public static final String TASK_STATE_CLOSED_SUCCESS = "ClosedSuccess";

	public static final String TASK_SUB_STATE_CLOSED_FAIL_AGENCY = "ANOTHER_INSURANCE_AGENCY";

	public static final String TASK_SUB_STATE_CLOSED_FAIL_INSURANCE_COMPANY = "ANOTHER_INSURANCE_COMPANY";

	public static final String TASK_SUB_STATE_CLOSED_FAIL_DIGITAL_AGENCY = "DIGITAL_INSURANCE_COMPANY";


	public static final String TASK_STATE_CLOSED_FAIL = "ClosedFail";
	public static final String TASK_SUB_STATE_EXPIRED = "AUTO_SHUTDOWN_TIMEOUT";

	public static final String TASK_SUB_STATE_CANCEL_POLICY= "CANCEL_POLICY";

	public static final String TASK_PROCESS_HISTORY_USERNAME = "kampus_scheduler";
	public static final String TASK_PROCESS_HISTORY_FULL_NAME = "kampus";

	public static final String AFFINITY_ATTRIBUTE_NAME = "affinity";
	public static final Long AFFINITY_CAMPAIGN_TYPE = 4L;

	public static final Long PARTICIPATION_CAMPAIGN_TYPE = 3L;

	public static final String TASK_OWNER_CHANGED = "Sorumlu kişi değiştirildi -> ";

	public static final Long AFFINITY_ATTRIBUTE_ID = 118L;


	public static final String POLICY_STATUS_CANCELED= "İptal";
	public static final String POLICY_STATUS_ACTIVE= "Aktif";


}
