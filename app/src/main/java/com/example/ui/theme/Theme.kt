package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = Color.Black,
    primaryContainer = TerracottaDark,
    onPrimaryContainer = Color(0xFFFFDBCF),
    secondary = DarkSecondary,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF1B4320),
    onSecondaryContainer = Color(0xFFD2E8D3),
    tertiary = DarkTertiary,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF5A3900),
    onTertiaryContainer = Color(0xFFFEF3C7),
    background = DarkBackground,
    onBackground = Color(0xFFF3ECE4),
    surface = DarkSurface,
    onSurface = Color(0xFFF3ECE4),
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFD4C5B9),
    outline = Color(0xFF8C7E72)
)

private val LightColorScheme = lightColorScheme(
    primary = TerracottaPrimary,
    onPrimary = Color.White,
    primaryContainer = TerracottaLight,
    onPrimaryContainer = Color(0xFF3E0E02),
    secondary = VillageGreenSecondary,
    onSecondary = Color.White,
    secondaryContainer = VillageGreenLight,
    onSecondaryContainer = Color(0xFF0C270E),
    tertiary = GoldenYellowTertiary,
    onTertiary = Color.White,
    tertiaryContainer = GoldenYellowLight,
    onTertiaryContainer = Color(0xFF452200),
    background = WarmCreamBackground,
    onBackground = TextDarkBrown,
    surface = WarmYellowSurface,
    onSurface = TextDarkBrown,
    surfaceVariant = WarmYellowCard,
    onSurfaceVariant = TextMutedBrown,
    outline = BorderSand
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep traditional Tamil village warm theme consistent
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
