package com.malisoftware.components.container

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.malisoftware.backend.TestObjects
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class StoreContainerKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val store1 = "Store 1"
    val store2 = "Store 2"

    @Test
    fun storeContainer() {
        composeTestRule.setContent {
            StoreContainer(
                imageUrl1 = "",
                imageUrl2 = "",
                text1 = store1,
                text2 = store2
            )
        }
        composeTestRule.onNodeWithText(store1).assertExists()
        composeTestRule.onNodeWithText(store2).assertExists()
        composeTestRule
            .onNode(hasClickAction())

    }

    @Test
    fun storeContainer_imageClickListeners() {
        var clickedImage1 = false
        var clickedImage2 = false
        composeTestRule.setContent {
            StoreContainer(
                imageUrl1 = "",
                imageUrl2 = "",
                onImage1Click = { clickedImage1 = true },
                onImage2Click = { clickedImage2 = true },
                text1 = store1,
                text2 = store2
            )
        }
        // Simulate clicks
        composeTestRule.onNodeWithText(store1).performClick()
        composeTestRule.onNodeWithText(store2).performClick()
        // Check if click listeners were triggered
        assertTrue(clickedImage1)
        assertTrue(clickedImage2)
    }

    @Test
    fun storeContainer_customImageUrls() {
        val imageUrl1 = "https://upload.wikimedia.org/wikipedia/commons/b/bd/Test.svg"
        val imageUrl2 = "https://example.com/image2.jpg"
        composeTestRule.setContent {
            StoreContainer(
                imageUrl1 = imageUrl1,
                imageUrl2 = imageUrl2
            )
        }
        composeTestRule.onNodeWithContentDescription(imageUrl1).assertExists()
        composeTestRule.onNodeWithContentDescription(imageUrl2).assertExists()
    }

    @Test
    fun storeContainer_loading() {
        composeTestRule.setContent {
            StoreContainer(
                imageUrl1 = store1,
                imageUrl2 = "",
                loading = true
            )
        }
        composeTestRule.onNodeWithText(store2).assertDoesNotExist()
        //composeTestRule.onNodeWithTag(TestObjects.loadingTag).assertExists()
    }



    @Test
    fun storeText() {
    }

    @Test
    fun storeModifier() {
    }

    @Test
    fun storeContainer_() {
    }
}