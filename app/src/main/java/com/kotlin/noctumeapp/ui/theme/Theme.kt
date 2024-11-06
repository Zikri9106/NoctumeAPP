package com.kotlin.noctumeapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    onPrimary = BW10,
    primaryContainer = Blue30,
    onPrimaryContainer = Blue60,

    surface = BW10,
    onSurface = BW60,
    surfaceVariant = BW20,
    onSurfaceVariant = BW40,

    background = BW10,
    onBackground = BW60,

    outline = BW30,
    outlineVariant = BW20,

    error = Red40,
    onError = BW10,
    errorContainer = Red20,
    onErrorContainer = Red50,
)

@Composable
fun NoctumeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}