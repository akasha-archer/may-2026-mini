## Why

The order summary UI requires business logic and concurrency control to handle repeated rapid taps on the "Place Order" button. Without concurrency control, multiple asynchronous tasks could execute simultaneously, violating the challenge requirements. Introducing a ViewModel with coroutine-based concurrency control ensures single-shot execution, accurate interaction accounting, random operation delay simulation, and proper state reset mechanics.

## What Changes

- **Introduce `OrderUiState`**: Consolidate screen presentation state (`clickCount: Int`, `requestCount: Int`, `orderStatus: OrderStatus`) into an immutable data class.
- **Implement `OrderSummaryViewModel`**: Create a ViewModel maintaining `uiState` via Compose `MutableState`, exposing `onPlaceOrderClick()`.
- **Single-Shot Concurrency Control**: Guard background execution using `orderJob?.isActive == true`, ensuring only one simulated operation runs at a time regardless of rapid taps.
- **Dynamic Simulated Delay**: Simulate async task processing with a randomized delay between `MIN_DELAY_MS` (2000ms) and `MAX_DELAY_MS` (3000ms).
- **Completion & Reset Mechanics**: When in `OrderStatus.DONE`, a subsequent tap on "Place Order" resets counters before incrementing for the new operation and transitioning back to `OrderStatus.PROCESSING`.
- **Integration with MainActivity**: Bind `OrderSummaryViewModel` into `MainActivity` to supply live state and action callbacks to `OrderSummary`.

## Capabilities

### New Capabilities
- `order-concurrency-control`: Defines the business rules for single-shot execution, rapid tap handling, simulated asynchronous operation delay, and completion reset behavior.

### Modified Capabilities
<!-- None -->

## Impact

- `app/src/main/java/com/example/may_2026_project/presentation/OrderSummaryViewModel.kt`: New ViewModel and UI state definition.
- `app/src/main/java/com/example/may_2026_project/MainActivity.kt`: Bound to `OrderSummaryViewModel`.
- `app/src/main/java/com/example/may_2026_project/presentation/OrderSummaryLayout.kt`: Verified consumption of `OrderUiState`.
