package com.malisoftware.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.malisoftware.components.container.ColumnListContainer
import com.malisoftware.components.container.CoreListContainer
import com.malisoftware.components.container.GridListContainer
import com.malisoftware.components.container.ItemHeader
import com.malisoftware.components.container.ItemWithTitleBar
import com.malisoftware.components.container.RadioColumn
import com.malisoftware.components.container.RowListContainer
import com.malisoftware.components.container.SearchQueriesContainer
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.model.BusinessData
import com.malisoftware.model.Items
import com.malisoftware.theme.CardSizes
import com.malisoftware.theme.HorizontalDividerSizes
import org.junit.Rule
import org.junit.Test

class SmallBusinessContainerKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun smallBusinessContainer() {
        // Define test data
        val imageUrl = "https://example.com/image.jpg"
        val title = "Business Title"
        val subtitle = "Subtitle"

        // Launch the composable
        composeTestRule.setContent {
            SmallBusinessContainer(
                imageUrl = imageUrl,
                title = title,
                subtitle = subtitle,
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp),

            )
        }


        // Verify that the UI elements are displayed correctly
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(subtitle).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(imageUrl).assertExists()
        composeTestRule.onNodeWithContentDescription(imageUrl).assertHeightIsAtLeast(CardSizes.roundedBusinessPic)
        composeTestRule.onNodeWithContentDescription(imageUrl).assertWidthIsAtLeast(CardSizes.roundedBusinessPic)

        // You can add more assertions here based on your UI structure and requirements
    }

    @Test
    fun searchQueriesContainerktTest(){
        // Define test data
        val leadingIcon = Icons.Default.Search
        val trailingIcon = Icons.Default.Clear
        val text = "Search Query"

        // Launch the composable
        composeTestRule.setContent {
            SearchQueriesContainer(
                leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = leadingIcon.name) },
                trailingIcon = { Icon(imageVector = trailingIcon, contentDescription = trailingIcon.name) },
                text = text,
                showDivider = true
            )
        }

        // Verify that the UI elements are displayed correctly
        composeTestRule.onNodeWithContentDescription(leadingIcon.name).assertExists()
        composeTestRule.onNodeWithContentDescription(trailingIcon.name).assertExists()
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(leadingIcon.name).assertHasClickAction()
        composeTestRule.onNodeWithContentDescription(trailingIcon.name).assertHasClickAction()

        composeTestRule
            .onNode(
                hasTestTag("SearchQueriesContainerDivider"),
                useUnmergedTree = true
            )
            .assertExists()
            .assertWidthIsAtLeast(HorizontalDividerSizes.small)
            .assertHeightIsAtLeast(HorizontalDividerSizes.small)
            .assertHasNoClickAction()
            .assertIsDisplayed()

    }

    @Test
    fun radioColumnktTest (){
        // Define test data
        val options = listOf("Option 1", "Option 2", "Option 3")

        var selectedOption = ""
        // Launch the composable
        composeTestRule.setContent {
            RadioColumn(
                options = options.map { it to null },
                getSelected = { selectedOption = it }
            )
        }

        // Verify that the UI elements are displayed correctly
        options.forEach { option ->
            composeTestRule.onNodeWithText(option).assertIsDisplayed()
            composeTestRule.onNodeWithTag(option).assertHasClickAction()
        }

        composeTestRule.onNodeWithTag(options[0]).performClick()
        assert(selectedOption == options[0])


    }

    @Test
    fun coreListContainerKtTest(){
        // Define test data
        val title = "Categories"
        val trailingContent = { /*TODO*/ }
        val loading = false

        // Launch the composable
        composeTestRule.setContent {
            CoreListContainer(
                title = title,
                trailingContent = {  },
                loading = loading
            ){
                // Add your content here
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()

    }

    @Test
    fun columnListContainerKtTest(){
        // Define test data
        val title = "Categories"
        val trailingContent = { /*TODO*/ }
        val content = { /*TODO*/ }

        // Launch the composable
        composeTestRule.setContent {
            ColumnListContainer(
                title = title,
                trailingContent = {},
            ){
                items(10) { index ->
                    // Content of LazyColumn
                    Box(
                        modifier = Modifier
                            .testTag("box$index")
                            .height(50.dp)
                            .padding(4.dp)
                    )
                }
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        for (index in 0 until 10) {
            composeTestRule.onNodeWithTag("box$index").assertIsDisplayed()
        }
    }

    @Test
    fun gridListContainerKtTest(){
        // Define test data
        val title = "Categories"
        // Launch the composable
        composeTestRule.setContent {
            GridListContainer(
                title = title,
                trailingContent = {},
            ){
                items(10) { index ->
                    // Content of LazyColumn
                    Box(
                        modifier = Modifier
                            .testTag("box$index")
                            .height(50.dp)
                            .padding(4.dp)
                    )
                }
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        for (index in 0 until 10) {
            composeTestRule.onNodeWithTag("box$index").assertIsDisplayed()
        }
    }

    @Test
    fun rowListContainerKtTest(){
        // Define test data
        val title = "Categories"

        // Launch the composable
        composeTestRule.setContent {
            RowListContainer(
                title = title,
                trailingContent = {},
            ){
                items(10) { index ->
                    // Content of LazyColumn
                    Box(
                        modifier = Modifier
                            .testTag("box$index")
                            .height(50.dp)
                            .padding(4.dp)
                    )
                }
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        for (index in 0 until 10) {
            composeTestRule.onNodeWithTag("box$index").assertIsDisplayed()
        }
    }

    @Test
    fun itemWithTitleBarKtTest(){
        // Define test data
        val imageUrl = "https://example.com/image.jpg"
        val title = "Business Title"
        val subtitle = "Subtitle"
        val quantity = 5
        val onClick: (Items) -> Unit = { /*TODO*/ }

        // Launch the composable
        composeTestRule.setContent {
            ItemWithTitleBar(
                data = List(1){ Items(
                    imageUrl = imageUrl,
                    title = title,
                    quantity = quantity
                ) },
                onClick = onClick
            )
        }

        // Verify that the UI elements are displayed correctly
        composeTestRule.onNodeWithContentDescription(imageUrl).assertExists()
        composeTestRule.onNodeWithText(title).assertExists()
        composeTestRule.onNodeWithContentDescription(imageUrl).assertHasClickAction()
        composeTestRule.onNodeWithText(quantity.toString()).assertExists()
    }

    @Test
    fun itemHeaderKtTest(){



    }

}