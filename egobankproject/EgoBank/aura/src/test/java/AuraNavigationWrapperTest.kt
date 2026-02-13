package com.egobank.aura.ui.components

import AuraTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import components.AuraNavigationWrapper
import org.junit.Rule
import org.junit.Test

class AuraNavigationWrapperTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenSizeIsCompact_shouldShowBottomNavigationBar() {
        composeTestRule.setContent {
            AuraTheme {
                AuraNavigationWrapper(
                    windowWidthSizeClass = WindowWidthSizeClass.Compact,
                    selectedRoute = "home",
                    onRouteSelected = {}
                ) {
                    Text("Conteúdo da Tela")
                }
            }
        }

        // Verifica se os itens da NavigationBar (Bottom Bar) estão visíveis
        composeTestRule.onNodeWithText("Início").assertIsDisplayed()
        composeTestRule.onNodeWithText("Config").assertIsDisplayed()
        composeTestRule.onNodeWithText("Conteúdo da Tela").assertIsDisplayed()
    }

    @Test
    fun whenSizeIsExpanded_shouldShowNavigationRail() {
        composeTestRule.setContent {
            AuraTheme {
                AuraNavigationWrapper(
                    windowWidthSizeClass = WindowWidthSizeClass.Expanded,
                    selectedRoute = "home",
                    onRouteSelected = {}
                ) {
                    Text("Conteúdo da Tela")
                }
            }
        }

        // No NavigationRail, os itens também devem estar visíveis
        composeTestRule.onNodeWithText("Início").assertIsDisplayed()
        composeTestRule.onNodeWithText("Config").assertIsDisplayed()
    }

    @Test
    fun whenItemIsClicked_shouldCallOnRouteSelected() {
        var selectedRoute = ""

        composeTestRule.setContent {
            AuraTheme {
                AuraNavigationWrapper(
                    windowWidthSizeClass = WindowWidthSizeClass.Compact,
                    selectedRoute = "home",
                    onRouteSelected = { route -> selectedRoute = route }
                ) {
                    // Content
                }
            }
        }

        composeTestRule.onNodeWithText("Config").performClick()

        // Verifica se o callback retornou a rota correta
        assert(selectedRoute == "settings")
    }
}
