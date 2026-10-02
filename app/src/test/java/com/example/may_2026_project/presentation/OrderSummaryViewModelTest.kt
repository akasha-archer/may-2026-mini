package com.example.may_2026_project.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OrderSummaryViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialState_isIdleWithZeroCounts() {
        val viewModel = OrderSummaryViewModel()
        assertEquals(0, viewModel.uiState.clickCount)
        assertEquals(0, viewModel.uiState.requestCount)
        assertEquals(OrderStatus.IDLE, viewModel.uiState.orderStatus)
    }

    @Test
    fun rapidClicks_registersAllClicks_butStartsOnlyOneRequest() = runTest(testDispatcher) {
        val viewModel = OrderSummaryViewModel()

        // 1st click starts operation
        viewModel.onPlaceOrderClick()
        testDispatcher.scheduler.runCurrent()

        assertEquals(1, viewModel.uiState.clickCount)
        assertEquals(1, viewModel.uiState.requestCount)
        assertEquals(OrderStatus.PROCESSING, viewModel.uiState.orderStatus)
        assertTrue(viewModel.orderJob?.isActive == true)

        // Rapid subsequent clicks (e.g. 3 more taps)
        viewModel.onPlaceOrderClick()
        viewModel.onPlaceOrderClick()
        viewModel.onPlaceOrderClick()
        testDispatcher.scheduler.runCurrent()

        // All 4 clicks registered, but request count stays 1
        assertEquals(4, viewModel.uiState.clickCount)
        assertEquals(1, viewModel.uiState.requestCount)
        assertEquals(OrderStatus.PROCESSING, viewModel.uiState.orderStatus)

        // Advance past max simulated delay (3000ms)
        advanceTimeBy(3500)

        // Operation completes
        assertEquals(OrderStatus.DONE, viewModel.uiState.orderStatus)
        assertEquals(4, viewModel.uiState.clickCount)
        assertEquals(1, viewModel.uiState.requestCount)
    }

    @Test
    fun tappingInDoneState_resetsCountersAndStartsNewOperation() = runTest(testDispatcher) {
        val viewModel = OrderSummaryViewModel()

        // First operation
        viewModel.onPlaceOrderClick()
        advanceTimeBy(3500)
        assertEquals(OrderStatus.DONE, viewModel.uiState.orderStatus)

        // Tap again from DONE state
        viewModel.onPlaceOrderClick()
        testDispatcher.scheduler.runCurrent()

        // Counters reset and register the new operation
        assertEquals(1, viewModel.uiState.clickCount)
        assertEquals(1, viewModel.uiState.requestCount)
        assertEquals(OrderStatus.PROCESSING, viewModel.uiState.orderStatus)

        advanceTimeBy(3500)
        assertEquals(OrderStatus.DONE, viewModel.uiState.orderStatus)
    }
}
