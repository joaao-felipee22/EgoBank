package tokens

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

// Cores Base (Paleta)
val AuraPurple80 = Color(0xFFD0BCFF) // Light primary for Dark theme
val AuraPurple40 = Color(0xFF6650a4) // Dark primary for Light theme
val AuraBlack = Color(0xFF1C1B1F)
val AuraWhite = Color(0xFFFFFFFF)

val AuraGreen40 = Color(0xFF006A6A) // Exemplo de Tertiary para Light theme
val AuraGreen80 = Color(0xFF4CDADA) // Exemplo de Tertiary para Dark theme
val AuraRed40 = Color(0xFFBA1A1A) // Exemplo de Error para Light theme
val AuraRed80 = Color(0xFFFFB4AB) // Exemplo de Error para Dark theme
val AuraBlueGrey40 = Color(0xFF625B71) // Exemplo de Secondary para Light theme
val AuraBlueGrey80 = Color(0xFFCCC2DC) // Exemplo de Secondary para Dark theme

// Cores neutras e variantes de superfície/borda
val AuraGrey10 = Color(0xFFF7F2FA) // Um cinza bem claro, quase branco
val AuraGrey20 = Color(0xFFE6E0E9) // Cinza claro para Light surfaceVariant, outlineVariant
val AuraGrey30 = Color(0xFFCAC4D0) // Cinza médio claro para Light outline
val AuraGrey60 = Color(0xFF49454F) // Cinza médio escuro para Light onSurfaceVariant, Dark outline
val AuraGrey90 = Color(0xFF313033) // Cinza escuro, quase preto para Dark surfaceVariant, inverseSurface Light
val AuraGrey95 = Color(0xFF28272A) // Um cinza quase preto, mais escuro que 90 mas ligeiramente mais claro que AuraBlack

val AuraScrim = Color(0x801C1B1F) // 50% de opacidade de AuraBlack (scrim para Light theme)
val AuraDarkScrim = Color(0x80000000) // 50% de opacidade de preto (scrim para Dark theme)

data class AuraColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val error: Color,
    val onError: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
    val scrim: Color,
    val inversePrimary: Color,
    val inverseOnPrimary: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,
    val isLight: Boolean
)

val LightColorScheme = AuraColors(
    primary = AuraPurple40,
    onPrimary = AuraWhite,
    secondary = AuraBlueGrey40,
    onSecondary = AuraWhite,
    tertiary = AuraGreen40,
    onTertiary = AuraWhite,
    error = AuraRed40,
    onError = AuraWhite,
    background = AuraWhite,
    onBackground = AuraBlack,
    surface = AuraWhite,
    onSurface = AuraBlack,
    surfaceVariant = AuraGrey20,
    onSurfaceVariant = AuraGrey60,
    outline = AuraGrey30,
    outlineVariant = AuraGrey20,
    scrim = AuraScrim,
    inversePrimary = AuraPurple80,
    inverseOnPrimary = AuraBlack,
    inverseSurface = AuraGrey90,
    inverseOnSurface = AuraWhite,
    isLight = true
)

val DarkColorScheme = AuraColors(
    primary = AuraPurple80,
    onPrimary = AuraBlack,
    secondary = AuraBlueGrey80,
    onSecondary = AuraBlack,
    tertiary = AuraGreen80,
    onTertiary = AuraBlack,
    error = AuraRed80,
    onError = AuraBlack,
    background = AuraBlack,
    onBackground = AuraWhite,
    surface = AuraBlack,
    onSurface = AuraWhite,
    surfaceVariant = AuraGrey90,
    onSurfaceVariant = AuraGrey20,
    outline = AuraGrey60,
    outlineVariant = AuraGrey90,
    scrim = AuraDarkScrim,
    inversePrimary = AuraPurple40,
    inverseOnPrimary = AuraWhite,
    inverseSurface = AuraWhite,
    inverseOnSurface = AuraBlack,
    isLight = false
)

// Helper extension function para converter AuraColors para ColorScheme do Material Design 3
// Isso é crucial para que os componentes do Material Design usem suas cores.
fun AuraColors.toMaterialColorScheme(): ColorScheme {
    return ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primary,
        onPrimaryContainer = onPrimary,

        // Adicionando inversePrimary aqui, onde o construtor do ColorScheme espera (item 5)
        inversePrimary = inversePrimary,

        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondary,
        onSecondaryContainer = onSecondary,

        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiary,
        onTertiaryContainer = onTertiary,

        error = error,
        onError = onError,
        errorContainer = error,
        onErrorContainer = onError,

        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,

        // ADIÇÃO NECESSÁRIA: O parâmetro surfaceTint
        surfaceTint = primary, // Geralmente surfaceTint é a mesma que primary

        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,

        // Cores de superfície específicas do M3, você pode mapear para os seus AuraGrey conforme necessário
        surfaceDim = if (isLight) AuraWhite else AuraGrey90,
        surfaceBright = if (isLight) AuraWhite else AuraBlack,
        surfaceContainer = if (isLight) AuraGrey10 else AuraGrey90,
        surfaceContainerHigh = if (isLight) AuraGrey20 else AuraGrey60,
        surfaceContainerHighest = if (isLight) AuraGrey30 else AuraGrey60,
        surfaceContainerLow = if (isLight) AuraWhite else AuraGrey95,
        surfaceContainerLowest = if (isLight) AuraWhite else AuraBlack,
    )
}
