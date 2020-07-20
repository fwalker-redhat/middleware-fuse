package com.example.camel.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.kafka.KafkaManualCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManualCommit implements Processor {

    private static Logger LOGGER = LoggerFactory.getLogger(ManualCommit.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        Boolean lastOne = exchange.getIn().getHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT, Boolean.class);
        System.out.println("Body inside commit: " + exchange.getIn().getHeaders().toString());
        System.out.println("Offset: " + exchange.getIn().getHeader(KafkaConstants.MANUAL_COMMIT));

        if (lastOne) {
            KafkaManualCommit manual = exchange.getIn().getHeader(KafkaConstants.MANUAL_COMMIT, KafkaManualCommit.class);
            System.out.println("KafkaManualCommit: " + exchange.getIn().getHeader(KafkaConstants.MANUAL_COMMIT).toString());
            System.out.println("KafkaManualCommit: " + manual);
            if (manual != null) {
                LOGGER.info("manually committing the offset for batch");
                manual.commitSync();
                LOGGER.info("Manual Commit Performed");
            } else {
                LOGGER.info("Did not commit offset");
            }
        } else {
            LOGGER.info("NOT time to commit the offset yet");
        }
    }

}
