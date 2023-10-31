package com.camel.bill.example.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class InvoiceList {

    private List<Invoice> invoiceList =  new ArrayList<>();
}
