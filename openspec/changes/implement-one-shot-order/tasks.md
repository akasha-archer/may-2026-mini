## 1. Data Model and ViewModel Setup

- [ ] 1.1 Create `OrderUiState` data class with `clickCount: Int = 0`, `requestCount: Int = 0`, and `orderStatus: OrderStatus = OrderStatus.IDLE` in `OrderSummaryViewModel.kt` and verify file compiles.
- [ ] 1.2 Implement `OrderSummaryViewModel : ViewModel()` with `uiState` property backed by `mutableStateOf(OrderUiState())` and define `MIN_DELAY_MS = 2000L` and `MAX_DELAY_MS = 3000L` constants in companion object.

## 2. Concurrency Control and Action Logic

- [ ] 2.1 Implement `onPlaceOrderClick()` with unconditional click incrementing and reset logic when currently in `OrderStatus.DONE`.
- [ ] 2.2 Add `orderJob?.isActive == true` concurrency guard and launch single-shot coroutine updating `requestCount`, transitioning `orderStatus` to `PROCESSING`, executing `delay((MIN_DELAY_MS..MAX_DELAY_MS).random())`, and finishing in `DONE`.

## 3. UI Wiring & Verification

- [ ] 3.1 Bind `OrderSummaryViewModel` in `MainActivity.kt` using `by viewModels()` and pass state and action callback to `OrderSummary`.
- [ ] 3.2 Add unit test in `app/src/test/` verifying rapid calls to `onPlaceOrderClick()` register all clicks while launching only one job, verifying test passes with `./gradlew test`.
- [ ] 3.3 Verify full application assembly passes cleanly with `./gradlew assembleDebug`.
