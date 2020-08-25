package com.example.camel.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.kafka.KafkaManualCommit;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

public class ManualCommitTest extends CamelBlueprintTestSupport {

    private static final String BLUEPRINT_DESCRIPTOR = "OSGI-INF/blueprint/camel-routes.xml";
    private static final String EXCHANGE_BODY = "TestData";

    @Mock
    KafkaManualCommit kafkaManualCommit;

    @Before
    public void setup() {
        kafkaManualCommit = Mockito.mock(KafkaManualCommit.class);
    }

    @Override
    protected String getBlueprintDescriptor() {
        return BLUEPRINT_DESCRIPTOR;
    }

    @Test
    public void contextLoads() {
        assertTrue(context.getStatus().isStarted());
    }

    @Test
    public void givenExchangeWithoutTriggeringHeadersWhenManualCommitProcessorProcessesThenProcessIsCalled()
            throws InterruptedException {
        Exchange noHeaders = ExchangeBuilder.anExchange(context).withBody(EXCHANGE_BODY).build();
        assertNull(noHeaders.getIn().getHeader(KafkaConstants.MANUAL_COMMIT));
        assertNull(noHeaders.getIn().getHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT));
        template.send("direct:testInEndpoint", noHeaders);
        MockEndpoint mock = getMockEndpoint("mock:testOutEndpoint");
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied(60, TimeUnit.SECONDS);
    }

    @Test
    public void givenExchangeWithoutLastRecordBeforeCommitHeaderWhenManualCommitProcessorProcessesThenKafkaManualCommitCommitSyncIsNotCalled()
            throws InterruptedException {
        Exchange noLastRecordBeforeCommitHeader = ExchangeBuilder.anExchange(context)
                .withHeader(KafkaConstants.MANUAL_COMMIT, kafkaManualCommit).withBody(EXCHANGE_BODY).build();
        assertNull(noLastRecordBeforeCommitHeader.getIn().getHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT));
        template.send("direct:testInEndpoint", noLastRecordBeforeCommitHeader);
        MockEndpoint mock = getMockEndpoint("mock:testOutEndpoint");
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied(60, TimeUnit.SECONDS);
        Mockito.verifyZeroInteractions(kafkaManualCommit);
    }

    @Test
    public void givenExchangeWithLastRecordBeforeCommitHeaderSetToFalseWhenManualCommitProcessorProcessesThenKafkaManualCommitCommitSyncIsNotCalled()
            throws InterruptedException {
        Exchange noLastRecordBeforeCommitHeader = ExchangeBuilder.anExchange(context)
                .withHeader(KafkaConstants.MANUAL_COMMIT, kafkaManualCommit)
                .withHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT, false).withBody(EXCHANGE_BODY).build();
        template.send("direct:testInEndpoint", noLastRecordBeforeCommitHeader);
        MockEndpoint mock = getMockEndpoint("mock:testOutEndpoint");
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied(60, TimeUnit.SECONDS);
        Mockito.verify(kafkaManualCommit, Mockito.times(0)).commitSync();
    }

    @Test
    public void givenExchangeWithLastRecordBeforeCommitHeaderSetToTrueWhenManualCommitProcessorProcessesThenKafkaManualCommitCommitSyncIsCalled()
            throws InterruptedException {
        Exchange noLastRecordBeforeCommitHeader = ExchangeBuilder.anExchange(context)
                .withHeader(KafkaConstants.MANUAL_COMMIT, kafkaManualCommit)
                .withHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT, true).withBody(EXCHANGE_BODY).build();
        template.send("direct:testInEndpoint", noLastRecordBeforeCommitHeader);
        MockEndpoint mock = getMockEndpoint("mock:testOutEndpoint");
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied(60, TimeUnit.SECONDS);
        Mockito.verify(kafkaManualCommit, Mockito.times(1)).commitSync();
    }
}