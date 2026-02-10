package com.egobank.aura.ui.components

import AuraTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import components.AuraButton
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test

class AuraButtonTest {

    // Rule para testar Composables
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun auraButton_onClick_isTriggered() {
        var clicked = false
        composeTestRule.setContent {
            AuraTheme {
                AuraButton(
                    onClick = { clicked = true }, text = "Click Me"
                )
            }
        }

        composeTestRule.onNodeWithText("Click Me").performClick()

        assertThat(clicked, equalTo(true))
    }

    @Test
    fun auraButton_displaysCorrectText() {
        val buttonText = "My Button"
        composeTestRule.setContent {
            AuraTheme {
                AuraButton(
                    onClick = { /* Do nothing */ }, text = buttonText
                )
            }
        }

        composeTestRule.onNodeWithText(buttonText).assertExists()
    }

    @Test
    fun auraButton_disabled_doesNotTriggerClick() {
        var clicked = false
        composeTestRule.setContent {
            AuraTheme {
                AuraButton(
                    onClick = { clicked = true }, text = "Disabled Button", enabled = false
                )
            }
        }

        composeTestRule.onNodeWithText("Disabled Button").performClick()

        assertThat(clicked, equalTo(false))


        composeTestRule.onNodeWithText("Disabled Button").assert(hasNoClickAction())
    }

    @Test
    fun auraButton_enabled_hasClickAction() {
        composeTestRule.setContent {
            AuraTheme {
                AuraButton(
                    onClick = { /* Do nothing */ }, text = "Enabled Button", enabled = true
                )
            }
        }

        composeTestRule.onNodeWithText("Enabled Button").assert(hasClickAction())
    }

    @Test
    fun auraButton_acceptsCustomModifier() {
        composeTestRule.setContent {
            AuraTheme {
                AuraButton(
                    onClick = { /* Do nothing */ },
                    text = "Padded Button",
                    modifier = Modifier.padding(24.dp)
                )
            }
        }

        composeTestRule.onNodeWithText("Padded Button").assertExists()
    }

    @Test
    fun auraButton_acceptsCustomShape() {
        composeTestRule.setContent {
            AuraTheme {
                AuraButton(
                    onClick = { /* Do nothing */ },
                    text = "Squared Button",
                    shape = AuraTheme.shapes.extraLarge
                )
            }
        }
        composeTestRule.onNodeWithText("Squared Button").assertExists()
    }
}