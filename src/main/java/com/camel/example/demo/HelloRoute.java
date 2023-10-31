package com.camel.example.demo;

import org.apache.camel.BindToRegistry;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
//@BindToRegistry
public class HelloRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/")
                .produces("text/plain")
                .get("hello")
                .to("direct:hello");

        from("direct:hello")
                .transform().simple("Hello. We are at: ${body}");
    }
}
