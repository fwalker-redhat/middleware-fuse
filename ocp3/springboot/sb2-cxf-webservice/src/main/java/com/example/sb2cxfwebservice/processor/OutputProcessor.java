package com.example.sb2cxfwebservice.processor;

import com.cleverbuilder.bookservice.GetAllBooksResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;

import java.util.ArrayList;

public class OutputProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        MessageContentsList result = (MessageContentsList) exchange.getIn().getBody();
        ArrayList resultArray = (ArrayList) result.get(0);
        GetAllBooksResponse.Book body = (GetAllBooksResponse.Book)resultArray.get(0);
        exchange.getMessage().setBody(body);
    }
}
