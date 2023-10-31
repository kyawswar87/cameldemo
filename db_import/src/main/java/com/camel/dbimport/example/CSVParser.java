package com.camel.dbimport.example;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class CSVParser {

    @Autowired
    InvoiceRepo invoiceRepo;

    public void downloadAndProcessFile(List<List<String>> csvData)
    {
        for (List<String> line : csvData) {
            Invoice invoice = Invoice.builder()
                                    .acctId(line.get(0))
                                    .advanceBalance(Integer.valueOf(line.get(1)))
                                    .currency(line.get(2))
                                    .customCode(line.get(3))
                                    .invoiceNo(line.get(4))
                                    .build();
            invoiceRepo.save(invoice);
            log.info(invoice);
        }
    }
}
