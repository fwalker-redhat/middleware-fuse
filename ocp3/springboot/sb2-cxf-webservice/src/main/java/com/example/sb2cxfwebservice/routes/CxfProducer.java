package com.example.sb2cxfwebservice.routes;

import com.example.sb2cxfwebservice.processor.InputProcessor;
import com.example.sb2cxfwebservice.processor.OutputProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfProducer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:defaultTimer?fixedRate=true&period=1000")
                .process(new InputProcessor())
                .to("log:logger?level=INFO&showAll=true")
                .to("cxf:bean:customCxfEndpoint?loggingFeatureEnabled=true&defaultOperationName=AddBook&wrappedStyle=true")
                .to("log:logger?level=INFO&showAll=true")
                .to("cxf:bean:customCxfEndpoint?loggingFeatureEnabled=true&defaultOperationName=GetAllBooks")
                .process(new OutputProcessor())
                .to("log:logger?level=INFO&showAll=true");
    }

}
