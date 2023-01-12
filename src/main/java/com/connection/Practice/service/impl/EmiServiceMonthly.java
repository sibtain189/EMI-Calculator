package com.connection.Practice.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.connection.Practice.model.AmortizationDetailsModel;
import com.connection.Practice.model.EmiModel;
import com.connection.Practice.model.EmiResponseModel;
import com.connection.Practice.service.IEmiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmiServiceMonthly implements IEmiService{

	
	Integer p;
	Integer t;
	double r;
	double calculatedEMI;
	double amountPay;
	double interestPay;
	double tenure;
	double balance;
	final DecimalFormat df = new DecimalFormat("0.0000");
	int monthNumber = 1;
	/**
	 * This method is used to calculate EMI
	 */
	@Override
	public void calculatedEMI(Integer p, double r, Integer t) throws Exception {
		try {
			calculatedEMI = p * r / 100 * Math.pow(1 + r / 100, t) / (Math.pow(1 + r / 100, t) - 1);
			amountPay = calculatedEMI * t;
			interestPay = amountPay - p;
		} catch (Exception e) {
			log.error("error in calculating EMI",e);
		}
	}

	/**
	 * this method is used to generate the amortization schedule
	 * 
	 * @param calculatedEMI variable to store the calculated EMI
	 * 
	 * @param amountPay variable to store the Total amount to pay after specific
	 * interest charges
	 * 
	 * @param InterestPay variable to store the total interest pay on the given loan
	 * amount
	 * 
	 * @param tenure variable to store the loan period by years
	 * 
	 * @param balance variable to check the remaining outstanding balance after each
	 * EMI deposit
	 */

	@Override
	public EmiResponseModel GenerateEMI(EmiModel model) throws Exception {
		log.info("Service class execution initiated");

		List<AmortizationDetailsModel> Amortizationtable = new ArrayList<>();

		p = model.getLoanAmount();
		r = (model.getRateOfInterest() / 12);
		t = model.getTenure();
		try {

			calculatedEMI(p, r, t);
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			String formattedDate = format.format(new Date());
			int year = Integer.parseInt(formattedDate);

			Month month = Month.from(LocalDate.now());
			int currentMonth = month.getValue();
			tenure = model.getTenure();
			if (currentMonth != 1) {
				tenure = tenure + 1;
			}
			for (int i = 0; i < tenure*12; i++) {

				AmortizationDetailsModel modelDetails = new AmortizationDetailsModel();
				modelDetails.setMonthNumber(monthNumber++);
				modelDetails.setCurrentYear(year);

				String nextMonth = month.plus(i).name();
				modelDetails.setCurrentMonth(nextMonth);

				if (nextMonth.equals("DECEMBER"))
					year++;

				double paidInterest = p * (model.getRateOfInterest() / (12 * 100));
				double paidPrincipal = calculatedEMI - paidInterest;
				balance = amountPay - calculatedEMI;

				modelDetails.setEmi(Double.parseDouble(df.format(calculatedEMI)));
				modelDetails.setInterest(Double.parseDouble(df.format(paidInterest)));
				modelDetails.setPrincipal(Double.parseDouble(df.format(paidPrincipal)));
				modelDetails.setOutstanding(Double.parseDouble(df.format(balance)));

				Amortizationtable.add(modelDetails);
				p = (int) (p - paidPrincipal);
				amountPay = balance;

				if (modelDetails.getOutstanding() <= 0)
					break;
			}
			log.info("Emi calculated");
			log.info("execution completed");
			monthNumber=1;
		} catch (Exception e) {
			log.error("Error in service class implementation", e);
		}
		/**
		 * Service level object of EmiResponseModel
		 */
		return EmiResponseModel.builder().status(HttpStatus.OK.value())
				.message("EMI Calculation is successfully completed").emi(Math.round(calculatedEMI))
				.interest(Math.round(interestPay)).TotalAmountPayable(Math.round(calculatedEMI * t))
				.schedule(Amortizationtable).build();
	}
}
