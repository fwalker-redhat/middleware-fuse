package com.example.sb2cxfwebservice.endpoint;

import com.cleverbuilder.bookservice.BookService;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    CxfEndpoint customCxfEndpoint() {
        CxfEndpoint customCxfEndpoint = new CxfEndpoint();
        customCxfEndpoint.setAddress("{{customEndpoint.url}}");
        customCxfEndpoint.setServiceClass(BookService.class);
        return customCxfEndpoint;
    }

}
