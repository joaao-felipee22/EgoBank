package components

import AuraTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

// Dados dos itens de navegação
data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuraNavigationWrapper(
    windowWidthSizeClass: WindowWidthSizeClass,
    selectedRoute: String,
    onRouteSelected: (String) -> Unit,
    content: @Composable () -> Unit
) {
    val items = listOf(
        NavigationItem("Início", Icons.Default.Home, "home"),
        NavigationItem("Config", Icons.Default.Settings, "settings")
    )

    // Se for celular (Compact), usa coluna para colocar a barra embaixo
    // Se for Tablet/Desktop (Medium ou Expanded), usa Row para colocar o Rail na lateral
    if (windowWidthSizeClass == WindowWidthSizeClass.Compact) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedRoute == item.route,
                            onClick = { onRouteSelected(item.route) },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        ) { padding ->
            // Área de conteúdo
            Surface(modifier = Modifier.fillMaxSize(), content = content)
        }
    } else {
        // Layout para Tablets (Navigation Rail na lateral)
        Row(modifier = Modifier.fillMaxSize()) {
            NavigationRail {
                items.forEach { item ->
                    NavigationRailItem(
                        selected = selectedRoute == item.route,
                        onClick = { onRouteSelected(item.route) },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
            // Área de conteúdo ao lado do Rail
            Surface(modifier = Modifier.fillMaxSize(), content = content)
        }
    }
}

@Preview(showBackground = true, name = "Celular (Compact)")
@Composable
fun PreviewAuraNavigationMobile() {
    AuraTheme {
        AuraNavigationWrapper(
            windowWidthSizeClass = WindowWidthSizeClass.Compact,
            selectedRoute = "home",
            onRouteSelected = {}
        ) {
            // Simulação de conteúdo da tela
            Text("Conteúdo na Visualização de Celular")
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=1280dp,height=800dp,dpi=240", // Especificação manual para Tablet
    name = "Tablet (Expanded)"
)
@Composable
fun PreviewAuraNavigationTablet() {
    AuraTheme {
        AuraNavigationWrapper(
            windowWidthSizeClass = WindowWidthSizeClass.Expanded,
            selectedRoute = "settings",
            onRouteSelected = {}
        ) {
            // Simulação de conteúdo da tela
            Text("Conteúdo na Visualização de Tablet")
        }
    }
}