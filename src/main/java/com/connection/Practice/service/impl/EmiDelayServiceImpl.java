package com.connection.Practice.service.impl;


import org.springframework.stereotype.Service;

import com.connection.Practice.model.EmiDelayModel;
import com.connection.Practice.model.EmiDelayResponseModel;
import com.connection.Practice.service.IEmiDelayService;


@Service
public class EmiDelayServiceImpl implements IEmiDelayService {

	
	
	


	

	@Override
	public EmiDelayResponseModel calculateDelayedInterest(EmiDelayModel emiDelayModel) throws Exception {
		double emi = emiDelayModel.getEmi();
		int days = emiDelayModel.getNumberOfDaysDelayed();
		int rateOfInterest = emiDelayModel.getPenaltyRateOfInterest();

		double penaltyForDelay = ((emi)*rateOfInterest)/100;
		double extraInterest = penaltyForDelay*days;
		double newOutStanding = emi + extraInterest;
		return new EmiDelayResponseModel(newOutStanding,emi,extraInterest);	 
	
		
	}


	}


