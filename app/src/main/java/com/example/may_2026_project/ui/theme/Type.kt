package com.example.may_2026_project.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.may_2026_project.R

val DmSans = FontFamily(
    Font(R.font.dm_sans_regular, FontWeight.Normal),
    Font(R.font.dm_sans_medium, FontWeight.Medium),
    Font(R.font.dm_sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.dm_sans_bold, FontWeight.Bold)
)

val AppTitle = TextStyle(
    fontFamily = DmSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 22.sp,
    lineHeight = 26.4.sp
)

val AppSubtitle = TextStyle(
    fontFamily = DmSans,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 20.8.sp
)

val AppBody = TextStyle(
    fontFamily = DmSans,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 19.6.sp
)

val AppLabel = TextStyle(
    fontFamily = DmSans,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 15.6.sp
)

// Material typography styles mapped to DM Sans
val Typography = Typography(
    titleLarge = AppTitle,
    titleMedium = AppSubtitle,
    bodyLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = AppBody,
    labelSmall = AppLabel,
    labelMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)