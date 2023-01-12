package com.connection.Practice.model;




import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmiDelayModel {
	
	
private double emi;

@NotNull(message = "Number od Days cannot be null")
@Range(min=1,max=29,message = "Please enter valid number of days")
@Digits(fraction = 0, integer = 3,message = "Please enter valid number of days i.e. only two decimal digits are allowed")
private int numberOfDaysDelayed;

@NotNull(message = "rate of interest cannot be null")
@Range(min=1,max=100,message = "Please enter valid rate of interest")
@Digits(fraction = 2, integer = 3,message = "Please enter valid rate of interest i.e. only two decimal digits are allowed")
private int penaltyRateOfInterest;

}
