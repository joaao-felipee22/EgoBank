package components

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

// Dados dos itens de navegação
data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

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