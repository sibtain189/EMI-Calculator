package com.connection.Practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForeclosureModel {

    private Integer loanAmount;

    private double rateOfInterest;

    private Integer tenure;

    private Integer installmentPaid;

}