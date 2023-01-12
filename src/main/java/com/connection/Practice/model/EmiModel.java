package com.connection.Practice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
//@AllArgsConstructor(staticName = "build")
@AllArgsConstructor
public class EmiModel {
	

	Integer loanAmount;
	double rateOfInterest;
	Integer tenure;

	
	}
