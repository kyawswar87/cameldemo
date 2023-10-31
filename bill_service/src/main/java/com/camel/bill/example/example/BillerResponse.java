package com.camel.bill.example.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillerResponse {

    private String invoiceId;
    private int amount;
    private int charge;
    private String status;
    private String txnID;
}
