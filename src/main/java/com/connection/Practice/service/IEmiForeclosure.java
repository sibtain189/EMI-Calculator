package com.connection.Practice.service;

import com.connection.Practice.model.ForeclosureModel;
import com.connection.Practice.model.ForeclosureResponseModel;

public interface IEmiForeclosure {

	public ForeclosureResponseModel GenerateEMI(ForeclosureModel model) throws Exception;
}
