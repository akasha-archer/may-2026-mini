package com.example.may_2026_project.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class OrderUiState(
    val clickCount: Int = 0,
    val requestCount: Int = 0,
    val orderStatus: OrderStatus = OrderStatus.IDLE
)

class OrderSummaryViewModel : ViewModel() {

    companion object {
        const val MIN_DELAY_MS = 2000L
        const val MAX_DELAY_MS = 3000L
    }

    var uiState by mutableStateOf(OrderUiState())
        private set

    var orderJob: Job? = null
        private set

    fun onPlaceOrderClick() {
        // Reset counters if previous operation completed
        if (uiState.orderStatus == OrderStatus.DONE) {
            uiState = uiState.copy(clickCount = 0, requestCount = 0)
        }

        // Tapping Place Order always registers a click
        uiState = uiState.copy(clickCount = uiState.clickCount + 1)

        // Concurrency guard: do not start another operation if one is already active
        if (orderJob?.isActive == true) {
            return
        }

        // Launch simulated background operation
        orderJob = viewModelScope.launch {
            uiState = uiState.copy(
                requestCount = uiState.requestCount + 1,
                orderStatus = OrderStatus.PROCESSING
            )

            val delayDuration = (MIN_DELAY_MS..MAX_DELAY_MS).random()
            delay(delayDuration)

            uiState = uiState.copy(orderStatus = OrderStatus.DONE)
        }
    }
}
