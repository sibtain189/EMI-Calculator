package com.connection.Practice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiDelayResponseModel {
double revisedEmiToPay;
double emi;
double penaltyCharges;
}
