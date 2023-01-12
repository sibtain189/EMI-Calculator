package com.connection.Practice.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiDelayDto {

	@NotBlank(message = "Enter your emi amount")
   @Pattern(regexp = "^\\s*-?[0-9]{1,10}\\s*$",message="Please enter only Integer value")
    @Size(min = 3, max = 10,message = "Please enter valid emi amount")
	private String emi;
	
	
	@NotNull(message = "Number od Days cannot be null")
    @Range(min=1,max=29,message = "Please enter valid number of days")
    @Digits(fraction = 0, integer = 3,message = "Please enter valid number of days i.e. only digits are allowed")
	private String numberOfDaysDelayed;
	
	@NotNull(message = "rate of interest cannot be null")
    @Range(min=1,max=100,message = "Please enter valid rate of interest")
    @Digits(fraction = 2, integer = 3,message = "Please enter valid rate of interest i.e. only two decimal digits are allowed")
	private int penaltyRateOfInterest;
}
