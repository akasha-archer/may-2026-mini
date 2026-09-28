## Purpose

Provides the UI action and status display layer for initiating order requests, inspecting interaction counters, and viewing operation progress without layout instability.

## ADDED Requirements

### Requirement: Place Order Action Trigger
The system SHALL display a "Place Order" button anchored at the bottom of the order summary screen. The button SHALL remain visually enabled and interactive at all times, including during ongoing operation processing.

#### Scenario: Visual persistence during processing
- **WHEN** an order operation is actively processing
- **THEN** the Place Order button remains visually enabled and does not display disabled styling or transformation

#### Scenario: Tapping Place Order registers user click
- **WHEN** the user taps the Place Order button
- **THEN** the action event is emitted to increment the total click count

### Requirement: Read-Only Debug Interaction Counters
The system SHALL present "Clicks" and "Requests started" as non-interactive display cards positioned below the order preview and above the action button.

#### Scenario: Debug displays are non-interactive
- **WHEN** the user taps on either the "Clicks" or "Requests started" displays
- **THEN** no secondary event or standalone action is triggered

#### Scenario: Reflecting interaction state
- **WHEN** the user taps the Place Order button
- **THEN** the "Clicks" display reflects total button taps and "Requests started" reflects only uniquely dispatched operations

### Requirement: Fixed-Height Order Status Indicator
The system SHALL provide an order status indicator located between the debug counters and the bottom action button. The status container SHALL maintain a constant vertical height across all lifecycle states to prevent layout shifts.

#### Scenario: Idle state maintains layout height
- **WHEN** the order action state is Idle
- **THEN** the status indicator reserves its vertical layout height without displaying active text or dots

#### Scenario: Processing status indication
- **WHEN** an order operation is currently executing
- **THEN** the indicator displays an orange status dot alongside "Processing..." text within the reserved height

#### Scenario: Completion status indication
- **WHEN** the order operation completes successfully
- **THEN** the indicator displays a green status dot alongside "Done" text within the reserved height
