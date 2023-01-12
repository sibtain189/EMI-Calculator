package com.connection.Practice.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.connection.Practice.exception.MaxPaidException;
import com.connection.Practice.model.AmortizationDetailsModel;
import com.connection.Practice.model.ForeclosureModel;
import com.connection.Practice.model.ForeclosureResponseModel;
import com.connection.Practice.service.IEmiForeclosure;

@Service
public class EmiForclosure implements IEmiForeclosure {

	Integer P;
	Integer T;
	double R;
	double amountPay;
	double interestPay;
	double calculatedEMI;
	double tenure;
	double balance;
	int monthNumber = 1;
	final DecimalFormat df = new DecimalFormat("0.0000");

	List<AmortizationDetailsModel> AmortizationTable = new ArrayList<>();

	@Override
	public ForeclosureResponseModel GenerateEMI(ForeclosureModel foreclosureModel) throws Exception {
		
		
		int emiCount = 1;
		double principalPaidAmount = 0;
		double interestPaid = 0;
		double foreclosureAmount = 0;

		ForeclosureResponseModel foreclosureResponseModel = new ForeclosureResponseModel();

		P = foreclosureModel.getLoanAmount();
		R = (foreclosureModel.getRateOfInterest() / 12);
		T = foreclosureModel.getTenure();

		if (T > foreclosureModel.getInstallmentPaid()) {

			try {

				calculatedEMI(P, R, T);

				SimpleDateFormat format = new SimpleDateFormat("yyyy");
				String formattedDate = format.format(new Date());
				int year = Integer.parseInt(formattedDate);

				Month month = Month.from(LocalDate.now());
				int currentMonth = month.getValue();
				tenure = foreclosureModel.getTenure();

				if (currentMonth != 1) {
					tenure = tenure + 1;
				}
				for (int i = 0; i < tenure * 12; i++) {

					AmortizationDetailsModel modelDetails = new AmortizationDetailsModel();
					modelDetails.setCurrentYear(year);
					modelDetails.setMonthNumber(monthNumber++);

					String nextMonth = month.plus(i).name();
					modelDetails.setCurrentMonth(nextMonth);

					if (nextMonth.equals("DECEMBER"))
						year++;

					double paidInterest = P * (foreclosureModel.getRateOfInterest() / (12 * 100));
					double paidPrincipal = calculatedEMI - paidInterest;
					balance = amountPay - calculatedEMI;

					if (foreclosureModel.getInstallmentPaid() >= emiCount) {
						principalPaidAmount += Double.parseDouble(df.format(paidPrincipal));
						interestPaid += Double.parseDouble(df.format(paidInterest));
						emiCount++;
					}

					// calculating Foreclosure amount
					foreclosureAmount = foreclosureModel.getLoanAmount() - principalPaidAmount;

					modelDetails.setEmi(Double.parseDouble(df.format(calculatedEMI)));
					modelDetails.setInterest(Double.parseDouble(df.format(paidInterest)));
					modelDetails.setPrincipal(Double.parseDouble(df.format(paidPrincipal)));

					if (Double.parseDouble(df.format(balance)) <= 0)
						modelDetails.setOutstanding(0.0);
					else
						modelDetails.setOutstanding(Double.parseDouble(df.format(balance)));

					// Addded data in Amortization table
					AmortizationTable.add(modelDetails);

					P = (int) (P - paidPrincipal);

					amountPay = balance;

					if (modelDetails.getOutstanding() <= 0) {
						break;
					}
				}

				foreclosureResponseModel.setForeclosureAmount((long) foreclosureAmount);
				foreclosureResponseModel.setMonthlyEmiPaid((long) calculatedEMI);
				foreclosureResponseModel.setInterestPaid((long) interestPaid);
				foreclosureResponseModel.setInterestSaved((long) (Math.round(interestPay) - interestPaid));
				foreclosureResponseModel.setBeforeForclosure(Math.round(interestPay));

			} catch (Exception e) {
				throw new Exception("Internal Server Error");
			}

		} else {

			throw new MaxPaidException("Paid EMI month is greater than or equal to tenure .....");
		}

		return foreclosureResponseModel;
	}

	public void calculatedEMI(Integer P, double R, Integer T) throws Exception {

		try {
			calculatedEMI = P * R / 100 * Math.pow(1 + R / 100, T) / (Math.pow(1 + R / 100, T) - 1);
			amountPay = calculatedEMI * T;
			interestPay = amountPay - P;
		} catch (Exception e) {
			throw new Exception("Internal Server Error");
		}
	}
}
