## 1. Theme and Typography Setup

- [x] 1.1 Add color definitions (`TerracottaOrange = Color(0xFFDE8A44)`, `StatusDone = Color(0xFF2E7D32)`, `WarmBackground = Color(0xFFF9F6F0)`, `TextMuted`, `TextPrimary`) to `Color.kt` and verify compilation succeeds.
- [x] 1.2 Configure `DmSans` `FontFamily` in `Type.kt` using existing `R.font.dm_sans_*` font resources (Regular, Medium, SemiBold, Bold) and verify typography references resolve cleanly.

## 2. Component Implementation

- [x] 2.1 Create the `OrderStatus` enum (`IDLE`, `PROCESSING`, `DONE`) and implement `OrderStatusIndicator` within a fixed-height container (`28.dp`) in `OrderSummaryLayout.kt` to guarantee zero vertical layout shift.
- [x] 2.2 Implement `DebugCounterCard` as a non-interactive surface in `OrderSummaryLayout.kt` to replace `UserButton`, verifying that clicks do not trigger ripples or standalone actions.
- [x] 2.3 Implement `PlaceOrderButton` in `OrderSummaryLayout.kt` conforming to the 56dp height, 50dp corner radius, `#DE8A44` background, and white text, remaining permanently enabled and clickable.

## 3. Screen Layout Assembly & Verification

- [x] 3.1 Refactor `UserInputButtonGroup` into `OrderActionSection`, adjusting the layout hierarchy with a `weight(1f)` spacer to anchor `PlaceOrderButton` at the bottom of the screen.
- [x] 3.2 Update `OrderSummary` root composable to `fillMaxSize()` and pass down state parameters (`clickCount`, `requestCount`, `orderStatus`, `onPlaceOrderClick`).
- [x] 3.3 Update Compose Previews in `OrderSummaryLayout.kt` covering `IDLE`, `PROCESSING`, and `DONE` screen states and verify the project builds successfully with `./gradlew compileDebugKotlin`.
