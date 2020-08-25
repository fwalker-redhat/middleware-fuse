package com.example.camel.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.kafka.KafkaManualCommit;

import java.util.Optional;

public class ManualCommit implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Optional.ofNullable(exchange.getIn().getHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT, Boolean.class))
                .map(b -> b ? exchange.getIn().getHeader(KafkaConstants.MANUAL_COMMIT, KafkaManualCommit.class) : null)
                .ifPresent(KafkaManualCommit::commitSync);
    }

}
