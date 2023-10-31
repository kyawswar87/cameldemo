package com.camel.dbimport.example;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class SFTPtoMySQLRoute extends RouteBuilder {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    InvoiceRepo invoiceRepo;

    @Override
    public void configure() throws Exception {

        from("sftp:10.17.217.136:22/upload_dir/test/init?move=../processed&moveFailed=../failed&fileName=data.csv&username=moee&password=00Y0nk}H\\fQd:RjG&streamDownload=true")

                // Process the file line by line
                .process(exchange -> {
                    try (InputStreamReader reader = new InputStreamReader(exchange.getIn().getBody(InputStream.class));
                         BufferedReader bufferedReader = new BufferedReader(reader)) {
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            // Process the line, e.g., transform, validate, or filter
                            // You can implement your custom logic here

                            CSVFormat.EXCEL.parse(bufferedReader)
                                    .stream().forEach(record -> {
                                        Invoice invoice = Invoice.builder()
                                                .acctId(record.get("acct_id"))
                                                .advanceBalance(Integer.valueOf(record.get("advance_balance")))
                                                .currency(record.get("currency"))
                                                .customCode(record.get("custom_code"))
                                                .invoiceNo(record.get("invoice_no"))
                                                .build();
                                        invoiceRepo.save(invoice);

                                    });
                        }
                    }
                })

                // Log the result (optional)
                .to("log:insertedIntoDatabase");
    }
}
