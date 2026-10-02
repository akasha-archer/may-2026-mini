package com.example.may_2026_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.may_2026_project.presentation.OrderSummary
import com.example.may_2026_project.presentation.OrderSummaryViewModel
import com.example.may_2026_project.ui.theme.May2026projectTheme

class MainActivity : ComponentActivity() {

    private val viewModel: OrderSummaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            May2026projectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state = viewModel.uiState
                    OrderSummary(
                        modifier = Modifier.padding(innerPadding),
                        clickCount = state.clickCount,
                        requestCount = state.requestCount,
                        orderStatus = state.orderStatus,
                        onPlaceOrderClick = viewModel::onPlaceOrderClick
                    )
                }
            }
        }
    }
}