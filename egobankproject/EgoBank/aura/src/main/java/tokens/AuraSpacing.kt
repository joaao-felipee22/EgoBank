package tokens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AuraSpacing(
    val none: Dp = 0.dp,
    val smallest: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val xLarge: Dp = 32.dp,
    val xxLarge: Dp = 48.dp,
    val xxxLarge: Dp = 64.dp
)

val LocalAuraSpacing = staticCompositionLocalOf { AuraSpacing() }