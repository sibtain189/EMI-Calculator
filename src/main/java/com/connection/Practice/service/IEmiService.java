package com.connection.Practice.service;

import com.connection.Practice.model.EmiModel;
import com.connection.Practice.model.EmiResponseModel;

public interface IEmiService {

	/**
	 * 
	 * @param model The object of EMimodel passed as a argument which contains loan
	 *              amount,rate of interest and tenure 
	 * @throws Exception Exception is thrown when there is some problem in EMI
	 *                   schedule
	 */
	public EmiResponseModel GenerateEMI(EmiModel model) throws Exception;

	/**
	 * 
	 * @param p variable to store the value of loan amount
	 * @param r variable to store the value of rate of interest
	 * @param t variable to store the value of tenure period in month's
	 * @throws Exception Exception is thrown when there is some problem in EMI
	 *                   calculation
	 */

	public void calculatedEMI(Integer p, double r, Integer t) throws Exception;
}
