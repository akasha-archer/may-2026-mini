package com.example.may_2026_project.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.may_2026_project.R

@Composable
fun OrderSummary(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        PageHeading()
        ProductListing(
            modifier = modifier
        )

        HorizontalDivider()
        UserInputButtonGroup()
    }
}

@Composable
fun PageHeading(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = Color.Black,
            contentDescription = "calendar",
            modifier = Modifier
                .scale(0.6f)
                .padding(start = 4.dp)
        )
        Text(
            text = "Order Summary",
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ProductListing(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.coffee_bag),
            contentDescription = "item for sale",
            modifier = Modifier
                .size(140.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp)
                .fillMaxWidth()
        ) {
            ItemText(itemText = "premium")
            ItemText(itemText = "blend")
            ItemText(itemText = "$24.99")
        }
    }
}


@Composable
fun UserInputButtonGroup(
    modifier: Modifier = Modifier,
    onClickCount: () -> Unit = {},
    onRequestCount: () -> Unit = {},
    clickCount: Int = 0,
    requestCount: Int = 0
) {
    Column(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Debug Info",
            modifier = Modifier.fillMaxWidth()
        )
        UserButton(
            buttonText = "Clicks",
            onItemClick = onClickCount,
            userInteractionCount = clickCount
        )

        UserButton(
            buttonText = "Requests Started",
            onItemClick = onRequestCount,
            userInteractionCount = requestCount
        )
    }
}

@Composable
fun ItemText(
    modifier: Modifier = Modifier,
    itemText: String
) {
    Text(
        text = itemText,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun UserButton(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {},
    buttonText: String,
    userInteractionCount: Int = 0,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12),
        onClick = onItemClick
    ) {
        Text(
            text = buttonText,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = userInteractionCount.toString(),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OrderPagePreview(
    modifier: Modifier = Modifier
) {
    OrderSummary()
}