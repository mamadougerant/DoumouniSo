package com.malisoftware.ai.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.malisoftware.ai.viewModel.ChatViewModel
import com.malisoftware.ai.presentation.ChatContainer
import com.malisoftware.ai.presentation.MessageField
import com.malisoftware.components.LoadingAnimation

@Preview
@Composable
fun ChatUi() {
    val chatViewModel: ChatViewModel = hiltViewModel()
    val chat = chatViewModel.chats.collectAsState()
    val loading by remember { chatViewModel.loading }
    val state = rememberLazyListState()

    Scaffold {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ){
            MessageField(
                onSendClick = {
                    chatViewModel.setRequest(it)
                },
                onMessageChange = {  },
                enabled = !loading
            )
            LazyColumn(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp)
            ) {
                items(chat.value.size) {
                    ChatContainer(
                        text = chat.value[it].message,
                        isAssistant = chat.value[it].isAssistant
                    )
                }
            }
            if (loading) {
                LoadingAnimation(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            LaunchedEffect(key1 = chat.value ,){
                state.animateScrollToItem(chat.value.size)
            }

        }
    }


}