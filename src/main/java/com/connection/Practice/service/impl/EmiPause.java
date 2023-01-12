package com.connection.Practice.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.connection.Practice.model.EmiPauseModel;
import com.connection.Practice.model.EmiPauseResponseModel;
import com.connection.Practice.service.IEmiPause;

@Service
public class EmiPause implements IEmiPause {
	double p;
	Integer t;
	Integer postPeriod;
	Integer totalLoanTenure;
	Integer numberOfEMI;
	Integer totalMoratoriumPeriod;
	double r;
	double calculatedEMI;
	double amountPay;
	double interestPay;
	double tenure;
	double balance;
	double increasedInterestIs;

	@Override
	public EmiPauseResponseModel calculatedEMIpause(EmiPauseModel pauseModel) throws Exception {

		try {
			p = pauseModel.getLoanAmount();
			r = (pauseModel.getRateOfInterest() / 12);
			t = (pauseModel.getTenure() * 12);
			numberOfEMI = pauseModel.getNoOfEmiPaid();
			totalMoratoriumPeriod = pauseModel.getMoratoriumPeriod();

			calculatedEMI = p * r / 100 * Math.pow(1 + r / 100, t) / (Math.pow(1 + r / 100, t) - 1);
			amountPay = calculatedEMI * t;
			interestPay = amountPay - p;
			double interestIs = 0;
			double totalInterest = 0;
			Integer increasedMonthCount = totalMoratoriumPeriod;

			for (int i = 1; i <= increasedMonthCount; i++) {
				interestIs = p * r / 100;
				p = p + interestIs;
				totalInterest += interestIs;
			}

			increasedInterestIs = totalInterest;

			postPeriod = (t - numberOfEMI) + totalMoratoriumPeriod;
			totalLoanTenure = t + totalMoratoriumPeriod;

		} catch (Exception e) {
			throw new Exception("Internal Server Error");
		}
		return EmiPauseResponseModel.builder().status(HttpStatus.OK.value())
				.message("EMI Calculation is successfully completed").emi(Math.round(calculatedEMI))
				.interest(Math.round(interestPay)).increasedInterest(Math.round(increasedInterestIs))
				.postMoratoriumPeriod(postPeriod).overAllLoanTenure(totalLoanTenure).build();
	}

}
