package com.connection.Practice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiPauseModel {

	Integer loanAmount;
	double rateOfInterest;
	Integer tenure;
	Integer noOfEmiPaid;
	Integer moratoriumPeriod;
	
}
