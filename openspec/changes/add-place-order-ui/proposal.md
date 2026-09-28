## Why

The current order summary screen lacks a primary action trigger ("Place Order"), displays interactive debug buttons instead of read-only counters, and has no visual state indicator for operation progress. Finalizing the UI with the specified button styling, read-only debug cards, and fixed-height status indicator provides the required foundation for one-shot action concurrency handling.

## What Changes

- **Add "Place Order" Button**: Add a primary action button anchored at the bottom of the screen (height: 56dp, pill shape with 50dp radius, container color `#DE8A44`, white medium text).
- **Persistent Button State**: The "Place Order" button remains visually enabled and clickable at all times, including during processing.
- **Refactor Debug Controls to Read-Only**: Replace interactive `UserButton`s with read-only counter cards for "Clicks" and "Requests started".
- **Fixed-Height Status Indicator**: Introduce `OrderStatusIndicator` with dedicated states (`IDLE`, `PROCESSING`, `DONE`), reserving a fixed vertical height to avoid layout jumps or flashing.
- **Rename & Restructure Action Composable**: Rename `UserInputButtonGroup` to `OrderActionSection`, restructuring the column with weight-based spacing to anchor the action button to the bottom.
- **Theme & Typography**: Define color constants for terracotta orange and status states in `Color.kt`, and configure the DM Sans font family in `Type.kt`.

## Capabilities

### New Capabilities
- `order-action-ui`: Covers the presentation components for the order action section, including the Place Order button, read-only debug counters, and the fixed-height status indicator.

### Modified Capabilities
<!-- None -->

## Impact

- `app/src/main/java/com/example/may_2026_project/presentation/OrderSummaryLayout.kt`: Refactored composables and updated layout structure.
- `app/src/main/java/com/example/may_2026_project/ui/theme/Color.kt`: New color tokens for button and status indicators.
- `app/src/main/java/com/example/may_2026_project/ui/theme/Type.kt`: DM Sans typography configuration.
