package com.camel.bill.example.example;

import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("billerservice")
@Log4j2
public class BillerService {

    @Autowired
    private RestTemplate restTemplate;

    private List<Invoice> saveList = new ArrayList<>();

    public Invoice[] getBiller(Exchange exchange) {
        String invoice = exchange.getMessage().getHeader("invoice", String.class);
        log.info("invoice : ", invoice);
        return restTemplate.getForObject(
                "http://localhost:8081/invoice?invoiceNo="+invoice, Invoice[].class);

    }

    public BillerResponse pay(Exchange exchange) {
        BillerRequest request = exchange.getMessage().getBody(BillerRequest.class);

        Invoice invoice = restTemplate.getForObject(
                "http://localhost:8081/invoice?invoiceId="+request.getInvoiceId(), Invoice.class);
        saveList.add(invoice);

        return BillerResponse.builder()
                .amount(invoice.getAdvanceBalance())
                .txnID(invoice.getId()+":"+invoice.getInvoiceNo())
                .invoiceId(invoice.getInvoiceNo())
                .charge(0)
                .status("success")
                .build();
    }
}
