package com.camel.bill.example.example;

import lombok.Data;

@Data
public class Invoice {
    private int id;
    private String acctId;
    private int advanceBalance;
    private String currency;
    private String customCode;
    private String invoiceNo;
}
