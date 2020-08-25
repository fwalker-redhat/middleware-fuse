package com.example.camel.kafka;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.kafka.KafkaManualCommit;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MyStepdefs {

    Exchange exchange;
    Processor manualCommitProcessor;

    @Mock
    KafkaManualCommit kafkaManualCommit;

    public MyStepdefs() {
        kafkaManualCommit = Mockito.mock(KafkaManualCommit.class);
    }

    @Given("Exchange Without Triggering Headers")
    public void exchangeWithoutTriggeringHeaders() {
        CamelContext camelContext = new DefaultCamelContext();
        exchange = new DefaultExchange(camelContext);
    }

    @When("Manual Commit Processor Processes")
    public void manualCommitProcessorProcesses() throws Exception {
        manualCommitProcessor = Mockito.spy(ManualCommit.class);
        manualCommitProcessor.process(exchange);
    }

    @Then("Process Is Called")
    public void processIsCalled() throws Exception {
        Mockito.verify(manualCommitProcessor, Mockito.times(1)).process(Mockito.any());
    }

    @Given("Exchange Without Last Record Before Commit Header")
    public void exchangeWithoutLastRecordBeforeCommitHeader() {
        CamelContext camelContext = new DefaultCamelContext();
        exchange = new DefaultExchange(camelContext);
        exchange.getIn().setHeader(KafkaConstants.MANUAL_COMMIT, kafkaManualCommit);
    }

    @And("Kafka Manual Commit commitSync Is Not Called")
    public void kafkaManualCommitCommitSyncIsNotCalled() {
        Mockito.verify(kafkaManualCommit, Mockito.times(0)).commitSync();
    }

    @Given("ExchangeWithLastRecordBeforeCommitHeaderSetToFalse")
    public void exchangewithlastrecordbeforecommitheadersettofalse() {
        CamelContext camelContext = new DefaultCamelContext();
        exchange = new DefaultExchange(camelContext);
        exchange.getIn().setHeader(KafkaConstants.MANUAL_COMMIT, kafkaManualCommit);
        exchange.getIn().setHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT, false);
    }

    @Given("Exchange With Last Record Before Commit Header Set To True")
    public void exchangeWithLastRecordBeforeCommitHeaderSetToTrue() {
        CamelContext camelContext = new DefaultCamelContext();
        exchange = new DefaultExchange(camelContext);
        exchange.getIn().setHeader(KafkaConstants.MANUAL_COMMIT, kafkaManualCommit);
        exchange.getIn().setHeader(KafkaConstants.LAST_RECORD_BEFORE_COMMIT, true);
    }

    @And("Kafka Manual Commit commitSync Is Called")
    public void kafkaManualCommitCommitSyncIsCalled() {
        Mockito.verify(kafkaManualCommit, Mockito.times(1)).commitSync();
    }
}
