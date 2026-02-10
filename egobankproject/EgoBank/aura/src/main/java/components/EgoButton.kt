package components

import AuraTheme
import androidx.compose.foundation.layout.padding
import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Um componente de botão simples que segue o Design System Aura.
 *
 * @param onClick A ação a ser executada quando o botão é clicado.
 * @param modifier O [Modifier] a ser aplicado a este botão.
 * @param enabled Controla a interação do usuário com o botão.
 * @param shape A forma do botão. Por padrão, usa `AuraTheme.shapes.medium`.
 * @param colors As cores para o botão. Por padrão, usa as cores primárias do `AuraTheme`.
 * @param contentPadding O preenchimento interno do conteúdo do botão.
 * @param text O texto a ser exibido no centro do botão.
 */
@Composable
fun AuraButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = AuraTheme.shapes.medium, // Usando uma das formas do seu Design System
    colors: ButtonColors = ButtonDefaults.buttonColors( // Usando as cores do seu Design System
        containerColor = AuraTheme.colors.primary,
        contentColor = AuraTheme.colors.onPrimary,
        disabledContainerColor = AuraTheme.colors.surfaceVariant, // Exemplo para desabilitado
        disabledContentColor = AuraTheme.colors.onSurfaceVariant // Exemplo para desabilitado
    ),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    text: String, // O texto do botão
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            style = AuraTheme.typography.labelLarge // Usando a tipografia do seu Design System para labels
        )
    }
}

// --- Pré-visualizações do componente ---

@Preview(showBackground = true, name = "AuraButton Light")
@Composable
fun AuraButtonPreviewLight() {
    AuraTheme {
        AuraButton(
            onClick = { /* Do something */ },
            text = "Click Me"
        )
    }
}

@Preview(showBackground = true, name = "AuraButton Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AuraButtonPreviewDark() {
    AuraTheme(darkTheme = true) {
        AuraButton(
            onClick = { /* Do something */ },
            text = "Click Me"
        )
    }
}

@Preview(showBackground = true, name = "AuraButton Disabled")
@Composable
fun AuraButtonPreviewDisabled() {
    AuraTheme {
        AuraButton(
            onClick = { /* Do something */ },
            enabled = false,
            text = "Disabled Button"
        )
    }
}

@Preview(showBackground = true, name = "AuraButton Custom Shape")
@Composable
fun AuraButtonPreviewCustomShape() {
    AuraTheme {
        AuraButton(
            onClick = { /* Do something */ },
            shape = AuraTheme.shapes.large, // Usando uma forma maior
            text = "Larger Rounded"
        )
    }
}

@Preview(showBackground = true, name = "AuraButton Fill Width")
@Composable
fun AuraButtonPreviewFillWidth() {
    AuraTheme {
        AuraButton(
            onClick = { /* Do something */ },
            modifier = Modifier.fillMaxWidth().
                // Exemplo para um pouco de padding lateral para o botão não colar na borda
                // Adicionar padding aqui é comum para botões que preenchem a largura
            padding(horizontal = 16.dp),
            text = "Fill Width Button"
        )
    }
}