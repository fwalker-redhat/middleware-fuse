package com.example.sb2cxfwebservice.processor;

import com.cleverbuilder.bookservice.AddBook;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InputProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        AddBook input = new AddBook();
        input.setAuthor("An Author Name");
        input.setTitle("Tile of a Book");
        exchange.getMessage().setBody(input);
    }

}
