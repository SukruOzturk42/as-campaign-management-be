package com.anadolusigorta.campaignmanagement.domain.operator;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;

import java.io.Serializable;

public enum OperatorEnum implements Serializable {
	EQ,LTE,GTE,IN,GT,LT,NEQ,NIN,BETWEEN,BETWEEN_DATE,EQ_BIRTH_DATE,NEQ_BIRTH_DATE,AGE,INLIST;

	public static OperatorEnum of(String name){
		switch (name.toUpperCase()){
		case "EQ" :return EQ;
		case "GTE" :return GTE;
		case "LTE" :return LTE;
		case "IN" :return IN;
		case "GT" :return GT;
		case "LT" :return LT;
		case "NEQ" :return NEQ;
		case "NIN" :return NIN;
		case "BETWEEN" :return BETWEEN;
		case "BETWEEN_DATE" :return BETWEEN_DATE;
		case "EQ_BIRTH_DATE": return EQ_BIRTH_DATE;
		case "NEQ_BIRTH_DATE": return NEQ_BIRTH_DATE;
		case "INLIST": return INLIST;
		case "AGE": return AGE;
		default: throw new CampaignManagementException("operator.not.found");
		}
	}
}
