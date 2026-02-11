package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EgoFeecback(
    onRetry: () -> Unit,
    onClose: () -> Unit
) {
    // Usando os tokens de espaçamento
    val spacing = AuraTheme.spacing

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.medium) // 16.dp
        ) {
            IconButton(
                onClick = onClose,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Fechar",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ops! Algo deu errado",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(spacing.small)) // 8.dp

                Text(
                    text = "Não foi possível conectar ao servidor. Por favor, verifique sua conexão e tente novamente.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = spacing.xLarge) // 32.dp
                )

                Spacer(modifier = Modifier.height(spacing.large)) // 24.dp

                Button(
                    onClick = onRetry,
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(
                        horizontal = spacing.xLarge, // 32.dp
                        vertical = spacing.small + spacing.smallest // 12.dp (8+4)
                    )
                ) {
                    Text(text = "Tentar novamente")
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "AuraButton Fill Width")
@Composable
fun EgoFeedbackPreview() {
    EgoFeecback(
        onClose = {},
        onRetry = {}
    )
}