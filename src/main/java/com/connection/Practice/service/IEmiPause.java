package com.connection.Practice.service;

import com.connection.Practice.model.EmiPauseModel;
import com.connection.Practice.model.EmiPauseResponseModel;
import com.connection.Practice.model.EmiResponseModel;

public interface IEmiPause {

	
	public EmiPauseResponseModel calculatedEMIpause(EmiPauseModel pausemodel) throws Exception;
}
