
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import tokens.AuraSpacing
import tokens.LocalAuraSpacing
import tokens.AuraColors
import tokens.DarkColorScheme
import tokens.LightColorScheme
import tokens.AuraShapes
import tokens.AuraTypography
import tokens.toMaterialColorScheme // Importe a função de extensão

/**
 * [LocalAuraColors] fornece uma forma de acessar as cores personalizadas do Design System Aura.
 * Ele permite que componentes que precisam de acesso direto às instâncias de [AuraColors] (não
 * convertidas para [ColorScheme] do Material Design 3) possam obtê-las.
 *
 * Por padrão, os componentes do Material Design 3 usarão [MaterialTheme.colorScheme], que é
 * derivado de [AuraColors]. No entanto, ter [LocalAuraColors] pode ser útil para lógicas
 * customizadas ou componentes que precisam da propriedade [AuraColors.isLight].
 */
val LocalAuraColors = staticCompositionLocalOf { LightColorScheme }

/**
 * [AuraTheme] é o ponto de entrada principal para aplicar o Design System Aura
 * à sua UI do Jetpack Compose.
 *
 * @param darkTheme Determina se o tema escuro deve ser aplicado. Por padrão, usa a configuração
 *                  do sistema ([isSystemInDarkTheme]).
 * @param dynamicColor Se true, tenta usar cores dinâmicas do Material You (apenas Android 12+).
 *                     Se não disponível ou false, volta para os esquemas de cores Aura predefinidos.
 * @param content O conteúdo Composable ao qual o tema será aplicado.
 */
@Composable
fun AuraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val materialColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme.toMaterialColorScheme()
        else -> LightColorScheme.toMaterialColorScheme()
    }

    val customAuraColors = when (darkTheme) {
        true -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = materialColorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    val spacing = AuraSpacing()

    CompositionLocalProvider(
        LocalAuraSpacing provides spacing,
        LocalAuraColors provides customAuraColors
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = AuraTypography,
            shapes = AuraShapes,
            content = content
        )
    }
}

/**
 * Objeto auxiliar para acessar facilmente os tokens do Design System Aura dentro de Composable.
 * Exemplo de uso: `AuraTheme.colors.primary`
 */
object AuraTheme {
    val colors: AuraColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAuraColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    val spacing: AuraSpacing
        @Composable
        get() = LocalAuraSpacing.current
}