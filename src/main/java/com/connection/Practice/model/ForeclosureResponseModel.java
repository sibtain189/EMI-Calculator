package com.connection.Practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ForeclosureResponseModel {

    private long foreclosureAmount;
    private long monthlyEmiPaid;
    private long interestPaid;
    private long interestSaved;
    private long beforeForclosure;
    

    
}