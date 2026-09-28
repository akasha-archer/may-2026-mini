package com.example.may_2026_project.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.may_2026_project.R
import com.example.may_2026_project.ui.theme.AppBody
import com.example.may_2026_project.ui.theme.AppLabel
import com.example.may_2026_project.ui.theme.AppSubtitle
import com.example.may_2026_project.ui.theme.AppTitle
import com.example.may_2026_project.ui.theme.BackgroundWarm
import com.example.may_2026_project.ui.theme.OnPrimary
import com.example.may_2026_project.ui.theme.OnSurfaceDark
import com.example.may_2026_project.ui.theme.OnSurfaceVariant
import com.example.may_2026_project.ui.theme.PrimaryOrange
import com.example.may_2026_project.ui.theme.StatusSuccess
import com.example.may_2026_project.ui.theme.SurfaceWhite

enum class OrderStatus {
    IDLE,
    PROCESSING,
    DONE
}

@Composable
fun OrderSummary(
    modifier: Modifier = Modifier,
    clickCount: Int = 0,
    requestCount: Int = 0,
    orderStatus: OrderStatus = OrderStatus.IDLE,
    onPlaceOrderClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundWarm)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        PageHeading(onBackClick = onBackClick)
        Spacer(modifier = Modifier.height(16.dp))
        ProductListing()
        OrderActionSection(
            modifier = Modifier.weight(1f),
            clickCount = clickCount,
            requestCount = requestCount,
            orderStatus = orderStatus,
            onPlaceOrderClick = onPlaceOrderClick
        )
    }
}

@Composable
fun PageHeading(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = OnSurfaceDark,
                contentDescription = "Navigate back"
            )
        }
        Text(
            text = "Order Summary",
            style = AppTitle,
            color = OnSurfaceDark,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProductListing(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.coffee_bag),
                contentDescription = "Premium Coffee Pack",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Premium Coffee Pack",
                    style = AppSubtitle,
                    color = OnSurfaceDark
                )
                Text(
                    text = "Arabica blend · 250g",
                    style = AppBody,
                    color = OnSurfaceVariant
                )
                Text(
                    text = "$24.99",
                    style = AppSubtitle.copy(fontWeight = FontWeight.Bold),
                    color = OnSurfaceDark
                )
            }
        }
    }
}

@Composable
fun OrderActionSection(
    modifier: Modifier = Modifier,
    clickCount: Int = 0,
    requestCount: Int = 0,
    orderStatus: OrderStatus = OrderStatus.IDLE,
    onPlaceOrderClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Text(
            text = "Debug Info",
            style = AppLabel,
            color = OnSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DebugCounterCard(
            label = "Clicks",
            count = clickCount,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DebugCounterCard(
            label = "Requests started",
            count = requestCount,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OrderStatusIndicator(
            status = orderStatus
        )
        Spacer(modifier = Modifier.weight(1f))
        PlaceOrderButton(
            onClick = onPlaceOrderClick
        )
    }
}

@Composable
fun DebugCounterCard(
    modifier: Modifier = Modifier,
    label: String,
    count: Int
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceWhite
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = AppBody,
                color = OnSurfaceVariant
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = count.toString(),
                style = AppSubtitle.copy(fontWeight = FontWeight.Bold),
                color = OnSurfaceDark
            )
        }
    }
}

@Composable
fun OrderStatusIndicator(
    status: OrderStatus,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(28.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        when (status) {
            OrderStatus.IDLE -> {
                // Fixed height placeholder prevents vertical layout jumps
            }
            OrderStatus.PROCESSING -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(color = PrimaryOrange, shape = CircleShape)
                    )
                    Text(
                        text = "Processing...",
                        style = AppBody,
                        color = OnSurfaceVariant
                    )
                }
            }
            OrderStatus.DONE -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(color = StatusSuccess, shape = CircleShape)
                    )
                    Text(
                        text = "Done",
                        style = AppBody,
                        color = OnSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun PlaceOrderButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        enabled = true, // Must remain visually enabled and clickable at all times per spec
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryOrange
        )
    ) {
        Text(
            text = "Place Order",
            style = AppSubtitle.copy(fontWeight = FontWeight.Medium),
            color = OnPrimary
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OrderPageIdlePreview() {
    OrderSummary(
        clickCount = 0,
        requestCount = 0,
        orderStatus = OrderStatus.IDLE
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OrderPageProcessingPreview() {
    OrderSummary(
        clickCount = 4,
        requestCount = 1,
        orderStatus = OrderStatus.PROCESSING
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OrderPageDonePreview() {
    OrderSummary(
        clickCount = 4,
        requestCount = 1,
        orderStatus = OrderStatus.DONE
    )
}