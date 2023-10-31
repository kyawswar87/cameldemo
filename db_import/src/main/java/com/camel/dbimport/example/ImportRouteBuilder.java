package com.camel.dbimport.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.springframework.stereotype.Component;

//@Component
public class ImportRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        CsvDataFormat csv = new CsvDataFormat();
        csv.setCaptureHeaderRecord(true);
        from("sftp:10.17.217.136:22/upload_dir/test/init?move=../processed&moveFailed=../failed&fileName=data.csv&username=moee&password=00Y0nk}H\\fQd:RjG")
                .unmarshal(csv)
                .bean(CSVParser.class)
                .stop();
    }
}
