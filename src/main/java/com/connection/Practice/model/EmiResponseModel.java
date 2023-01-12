package com.connection.Practice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")

public class EmiResponseModel {

	Integer status;
	String message;
	long emi;
	long interest;
	long TotalAmountPayable;

	List<AmortizationDetailsModel> schedule;
}
