package com.egobank.aura.ui.components


import AuraTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import components.EgoFeecback
import org.junit.Rule
import org.junit.Test

class EgoFeedbackTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayErrorMessagesAndButton() {
        composeTestRule.setContent {
            AuraTheme {
                EgoFeecback(onRetry = {}, onClose = {})
            }
        }

        // Verifica se o título e a descrição aparecem
        composeTestRule.onNodeWithText("Ops! Algo deu errado").assertIsDisplayed()
        composeTestRule.onNodeWithText("Não foi possível conectar ao servidor. Por favor, verifique sua conexão e tente novamente.")
            .assertIsDisplayed()

        // Verifica se o botão de tentar novamente aparece
        composeTestRule.onNodeWithText("Tentar novamente").assertIsDisplayed()
    }

    @Test
    fun whenRetryIsClicked_shouldInvokeCallback() {
        var retryCalled = false

        composeTestRule.setContent {
            AuraTheme {
                EgoFeecback(onRetry = { retryCalled = true }, onClose = {})
            }
        }

        // Simula o clique no botão
        composeTestRule.onNodeWithText("Tentar novamente").performClick()

        // Verifica se a variável mudou para true
        assert(retryCalled)
    }

    @Test
    fun whenCloseIsClicked_shouldInvokeCallback() {
        var closeCalled = false

        composeTestRule.setContent {
            AuraTheme {
                EgoFeecback(onRetry = {}, onClose = { closeCalled = true })
            }
        }

        // Simula o clique no ícone de fechar (X)
        // contentDescription que definimos no componente
        composeTestRule.onNodeWithContentDescription("Fechar").performClick()

        assert(closeCalled)
    }
}