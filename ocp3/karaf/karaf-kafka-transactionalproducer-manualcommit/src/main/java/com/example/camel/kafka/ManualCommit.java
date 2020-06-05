package com.example.camel.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.kafka.KafkaManualCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManualCommit implements Processor {

    private static Logger LOG = LoggerFactory.getLogger(ManualCommit.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            KafkaManualCommit manualCommit = exchange.getIn().getHeader(KafkaConstants.MANUAL_COMMIT, KafkaManualCommit.class);
            manualCommit.commitSync();
            LOG.info("Manual Commit Performed");
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            throw new Exception(e);
        }
    }

}
