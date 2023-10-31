package com.camel.bill.example.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class BillRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/api")
                .get("/enquiry")
                .param().name("invoice").type(RestParamType.query).endParam()
                .to("direct:enquiry")
                .post("/pay").type(BillerRequest.class)
                .to("direct:pay");

        from("direct:enquiry")
                .bean(BillerService.class,"getBiller")
                .marshal().json(Invoice[].class);

        from("direct:pay")
                .bean(BillerService.class, "pay")
                .marshal().json(BillerResponse.class);
    }
}
