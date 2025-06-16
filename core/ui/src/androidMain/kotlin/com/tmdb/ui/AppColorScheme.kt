package com.tmdb.ui

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tmdb.ui.AppColorScheme.Surface

/**
 * Created by van.luong
 * on 16,June,2025
 */
object AppColorScheme {
    val Background = Color(0xFF0A0F22)
    val Surface = Color(0xFF131B3A)
    val Primary = Color(0xFFFF3366)
    val PrimaryVariant = Color(0xFFE62E5C)
    val Secondary = Color(0xFF4D91FF)
    val OnPrimary = Color.White
    val TextPrimary = Color.White
    val TextSecondary = Color(0xFFB0B9D8)
    val Divider = Color.White.copy(alpha = 0.1f)
}

@Composable
fun searchTextFieldColors(): TextFieldColors = TextFieldDefaults.colors(
    focusedContainerColor = Surface,    // Dark background when focused
    unfocusedContainerColor = Surface,  // Same background when unfocused
    disabledContainerColor = Color(0xFF1C1C2E),

    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,

    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    disabledTextColor = Color.Gray,

    focusedPlaceholderColor = Color(0xFF9FA5C0),     // Grayish placeholder
    unfocusedPlaceholderColor = Color(0xFF9FA5C0),

    focusedLeadingIconColor = Color(0xFF9FA5C0),
    unfocusedLeadingIconColor = Color(0xFF9FA5C0),

    cursorColor = Color(0xFF00C4FF),     // Bright blue cursor
)