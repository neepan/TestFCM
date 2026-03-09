package com.neepan.testfcm.auth.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class LoginScreenTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginUI_displaysAllComponents(){
        //1. Tell the test Rule to display your screen
        composeTestRule.setContent {
            // We test the "Dumb" UI, passing a fake empty action for the button click!
            LoginScreenContent(onLoginClick = { username, password -> })
        }

        // 2. Assert that the specific texts exist on the screen

        composeTestRule.onNodeWithText("Welcome to Dustibun").assertIsDisplayed()
        composeTestRule.onNodeWithText("Username").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
    }


    @Test
    fun loginUI_allowsTyping(){
        composeTestRule.setContent {
            LoginScreenContent(onLoginClick = { _, _ -> })
        }

        //Find the username field , click it and type "myUsername"

        composeTestRule.onNodeWithText("Username")
            .performTextInput("myUsername")

        // Find the password field, click it, and type "password123"
        composeTestRule.onNodeWithText("Password")
            .performTextInput("password123")


        // Verify the text was actually entered
        composeTestRule.onNodeWithText("myUsername").assertExists()

    }
}

