## Context

The screen is an Android Jetpack Compose view ([OrderSummaryLayout.kt](file:///Users/akashaarcher/may2026project/app/src/main/java/com/example/may_2026_project/presentation/OrderSummaryLayout.kt)). Currently, the layout features a product preview and `UserInputButtonGroup` containing two clickable buttons (`UserButton`). The challenge requires building out the UI to test concurrency control: the primary action button ("Place Order") must remain visually enabled at all times, the debug statistics must be non-interactive display cards, and status updates must not cause layout jumps.

## Goals / Non-Goals

**Goals:**
- Implement `PlaceOrderButton` according to specifications: 56dp height, pill shape (50dp radius), `#DE8A44`, white text, permanently enabled and clickable.
- Refactor `UserInputButtonGroup` to `OrderActionSection`, anchoring the action button to the bottom using `Modifier.weight(1f)`.
- Replace clickable `UserButton` components with read-only `DebugCounterCard` surfaces.
- Introduce `OrderStatusIndicator` within a fixed-height container (`28.dp`) for stable rendering across `IDLE`, `PROCESSING`, and `DONE` states.
- Configure `TerracottaOrange` and status colors in `Color.kt` and integrate `DmSans` font family in `Type.kt`.

**Non-Goals:**
- Implementing the coroutine debounce and repository side-effects (scoped to subsequent implementation/apply work).
- Dark/light mode theme toggling or dynamic color overriding.
- Real network or payment gateway integrations.

## Decisions

### 1. Rename `UserInputButtonGroup` to `OrderActionSection`
- **Choice**: Rename the composable to `OrderActionSection`.
- **Rationale**: The component now houses read-only diagnostic readouts, a status indicator, and the single primary action button. The previous name implied a cluster of interactive buttons.
- **Alternatives Considered**: Keeping `UserInputButtonGroup` (rejected as misleading since the counters are not user inputs).

### 2. Fixed-Height Box for `OrderStatusIndicator`
- **Choice**: Wrap the indicator in a `Box(modifier = Modifier.height(28.dp))` that is always composed.
- **Rationale**: Displaying the indicator conditionally (`if (status != Idle)`) would cause a vertical shift in the layout when switching states. Reserving a fixed container height ensures zero layout shifting.
- **Alternatives Considered**: Animating visibility (`AnimatedVisibility`) or omitting space when idle (rejected due to flickering/layout shifts noted during exploration).

### 3. Read-Only `DebugCounterCard` Composable
- **Choice**: Use a `Surface` with `Row`, horizontal padding, and no `clickable` modifier.
- **Rationale**: Completely removes click ripples and accessibility click actions from the debug info, ensuring interaction is restricted solely to the Place Order button.
- **Alternatives Considered**: Using a disabled `Button` (rejected because disabled buttons have muted/dimmed opacity, violating visual parity with Figma designs).

### 4. Layout Weight Spacing
- **Choice**: Use `Spacer(modifier = Modifier.weight(1f))` between the status indicator and the Place Order button.
- **Rationale**: Guarantees that the Place Order button is anchored at the bottom of the screen across different device form factors while keeping the order preview and debug info grouped near the top.

## Risks / Trade-offs

- **[Risk] Persistently enabled button confusion** → *Mitigation*: The challenge intentionally mandates this behavior to test handling of repeated rapid clicks. Code comments will note that this simulates slow-device response delays.
- **[Trade-off] Fixed layout without scrolling** → *Mitigation*: Total vertical height of all components is under 420dp, well within standard Android screen sizes.
