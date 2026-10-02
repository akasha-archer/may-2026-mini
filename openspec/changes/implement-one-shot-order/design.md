## Context

The UI components for the order summary view are implemented in [OrderSummaryLayout.kt](file:///Users/akashaarcher/may2026project/app/src/main/java/com/example/may_2026_project/presentation/OrderSummaryLayout.kt), accepting stateless parameters for counters, status, and button clicks. Currently, [MainActivity.kt](file:///Users/akashaarcher/may2026project/app/src/main/java/com/example/may_2026_project/MainActivity.kt) renders the view with default values and no backing business logic or ViewModel.

## Goals / Non-Goals

**Goals:**
- Encapsulate screen state in an immutable `OrderUiState` data class.
- Implement `OrderSummaryViewModel` using Compose `mutableStateOf` for immediate reactivity.
- Enforce one-shot execution using coroutine `Job.isActive` concurrency control.
- Simulate asynchronous processing using a randomized delay between `MIN_DELAY_MS = 2000L` and `MAX_DELAY_MS = 3000L`.
- Handle reset mechanics when executing an order from `OrderStatus.DONE`.
- Connect `OrderSummaryViewModel` to `OrderSummary` in `MainActivity`.

**Non-Goals:**
- Network API calls, payment processing, or database persistence.
- Dependency injection framework setup (using `by viewModels()` from `androidx.activity`).

## Decisions

### 1. Unified `OrderUiState` Data Class
- **Choice**: Encapsulate `clickCount`, `requestCount`, and `orderStatus` in `OrderUiState`.
- **Rationale**: Enables atomic state updates via `copy(...)`. Resetting the screen from `DONE` can update counters and status in a single state emission.
- **Alternatives Considered**: Separate `mutableStateOf` fields for each property (rejected to avoid intermediate inconsistent states during reset).

### 2. Concurrency Guard using `Job.isActive`
- **Choice**: Maintain a `private var orderJob: Job? = null` and check `orderJob?.isActive == true`.
- **Rationale**: `Job.isActive` accurately tracks running coroutine execution. Taps arriving while `isActive` is true immediately return after incrementing the click counter, leaving the in-flight job unaffected.
- **Alternatives Considered**: `Mutex` or `AtomicBoolean` (rejected as unnecessarily complex since ViewModel operations run on the Main dispatcher).

### 3. Dynamic Delay Range `(MIN_DELAY_MS..MAX_DELAY_MS).random()`
- **Choice**: Define `MIN_DELAY_MS = 2000L` and `MAX_DELAY_MS = 3000L` in `companion object` and evaluate `(MIN_DELAY_MS..MAX_DELAY_MS).random()`.
- **Rationale**: Matches the 2–3s specification while providing realistic variation across requests.

### 4. Integration via `by viewModels()` in `MainActivity`
- **Choice**: Instantiate `OrderSummaryViewModel` using the Android KTX `by viewModels()` delegate in `MainActivity`.
- **Rationale**: Ensures the ViewModel survives configuration changes (such as device rotation) without adding third-party DI libraries.

## Risks / Trade-offs

- **[Risk] State resets lost if tap occurs precisely at completion boundary** → *Mitigation*: The `orderStatus == OrderStatus.DONE` check occurs synchronously on the main thread prior to launching the new coroutine.
- **[Trade-off] ViewModel imports Compose runtime `mutableStateOf`** → *Mitigation*: Accepted trade-off for simplicity and zero-boilerplate consumption in Jetpack Compose.
