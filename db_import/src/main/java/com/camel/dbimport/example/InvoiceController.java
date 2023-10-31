package com.camel.dbimport.example;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class InvoiceController {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @GetMapping("/invoice")
    public List<Invoice> getByInvoiceNo(@RequestParam String invoiceNo) {
       log.info("find by ", invoiceNo);
        return invoiceRepo.findByInvoiceNo(invoiceNo);
    }

}
