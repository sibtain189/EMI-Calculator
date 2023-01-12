package com.connection.Practice.model;

import javax.validation.GroupSequence;
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
@NoArgsConstructor
@AllArgsConstructor
@GroupSequence(value = {Emidto.class})
public class Emidto {

	@NotBlank(message = "Enter your loan amount")
    @Pattern(regexp = "^\\s*-?[0-9]{1,10}\\s*$",message="Please enter only Integer value")
    @Size(min = 5, max = 10,message = "Please enter valid loan amount")
    String loanAmount;

    @NotNull(message = "rate of interest cannot be null")
    @Range(min=1,max=100,message = "Please enter valid rate of interest")
    @Digits(fraction = 2, integer = 3,message = "Please enter valid rate of interest i.e. only two decimal digits are allowed")
    double rateOfInterest;
    
    @Pattern(regexp = "^\\s*-?[0-9]{1,10}\\s*$",message = "Please enter only Integer value")
    @NotBlank(message = "Tenure cannot be null")
    String tenure;
}
