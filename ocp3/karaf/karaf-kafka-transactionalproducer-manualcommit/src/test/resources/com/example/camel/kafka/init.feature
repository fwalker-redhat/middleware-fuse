Feature: Manual Commit on Kafka of a Camel Exchange with Custom Processor
   Messages consumed from Kafka should manually commit offsets rather than automatically committing offset

  Scenario: givenExchangeWithoutTriggeringHeadersWhenManualCommitProcessorProcessesThenProcessIsCalled
    Given Exchange Without Triggering Headers
    When Manual Commit Processor Processes
    Then Process Is Called

  Scenario: givenExchangeWithoutLastRecordBeforeCommitHeaderWhenManualCommitProcessorProcessesThenKafkaManualCommitCommitSyncIsNotCalled
    Given Exchange Without Last Record Before Commit Header
    When Manual Commit Processor Processes
    Then Process Is Called
    And Kafka Manual Commit commitSync Is Not Called

  Scenario: givenExchangeWithLastRecordBeforeCommitHeaderSetToFalseWhenManualCommitProcessorProcessesThenKafkaManualCommitCommitSyncIsNotCalled
    Given ExchangeWithLastRecordBeforeCommitHeaderSetToFalse
    When Manual Commit Processor Processes
    Then Process Is Called
    And Kafka Manual Commit commitSync Is Not Called

  Scenario: givenExchangeWithLastRecordBeforeCommitHeaderSetToTrueWhenManualCommitProcessorProcessesThenKafkaManualCommitCommitSyncIsCalled
    Given Exchange With Last Record Before Commit Header Set To True
    When Manual Commit Processor Processes
    Then Process Is Called
    And Kafka Manual Commit commitSync Is Called