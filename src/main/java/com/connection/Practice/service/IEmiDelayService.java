package com.connection.Practice.service;

import com.connection.Practice.model.EmiDelayModel;
import com.connection.Practice.model.EmiDelayResponseModel;

public interface IEmiDelayService {

	public EmiDelayResponseModel calculateDelayedInterest(EmiDelayModel emiDelayModel) throws Exception;
}
