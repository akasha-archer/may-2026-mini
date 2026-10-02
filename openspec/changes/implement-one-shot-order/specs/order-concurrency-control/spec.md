## Purpose

Controls the asynchronous execution lifecycle and concurrency constraints for order placement actions, ensuring single-shot processing and accurate interaction tracking.

## ADDED Requirements

### Requirement: Concurrency Guard for Order Processing
The system SHALL ensure that only one order operation executes at any given time. When an operation is active, subsequent taps SHALL NOT trigger duplicate or concurrent executions.

#### Scenario: Disallow parallel operations during processing
- **WHEN** an order operation is actively executing and the user taps "Place Order"
- **THEN** the ongoing operation continues without interruption and no duplicate operation is launched

#### Scenario: Launch operation when idle
- **WHEN** the system is in an Idle state and the user taps "Place Order"
- **THEN** a new background operation is initiated and the request count increments by 1

### Requirement: Interaction Counter Accounting
The system SHALL increment the click counter on every tap of the action button regardless of current processing state, and increment the request counter only when an operation actually starts.

#### Scenario: Rapid taps register clicks without increasing requests
- **WHEN** the user rapidly taps "Place Order" multiple times while an operation is running
- **THEN** the click counter increments on every tap and the request counter remains unchanged

### Requirement: Simulated Asynchronous Operation Duration
The system SHALL simulate order processing by executing an asynchronous delay randomly selected between 2000 milliseconds and 3000 milliseconds before transitioning to the completed state.

#### Scenario: Transition to Done after simulated delay
- **WHEN** an order operation begins
- **THEN** the system waits for the simulated duration between 2000ms and 3000ms and transitions to the Done state upon completion

### Requirement: Completion Reset Behavior
When the system is in the Done state and the user taps "Place Order", the system SHALL reset counters to zero before registering the new click, incrementing the request counter, and starting a new operation.

#### Scenario: Re-executing order from Done state
- **WHEN** the system is in the Done state and the user taps "Place Order"
- **THEN** the counters reset, the click count becomes 1, the request count becomes 1, and the state transitions to Processing
