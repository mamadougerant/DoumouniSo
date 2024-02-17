package com.malisoftware.ai.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malisoftware.ai.chatUseCase.ChatUseCase
import com.malisoftware.ai.model.Chat
import com.malisoftware.ai.model.request.ChatRequest
import com.malisoftware.components.uiEvent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase
) : ViewModel() {

    private val menu  = mutableStateOf("")

    private val _chats = MutableStateFlow<List<Chat>>(emptyList())
    val chats: StateFlow<List<Chat>> = _chats

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading


    // change to one function
    private fun message(message: String,isUser:Boolean = true): String {
        val role = if (isUser) "user" else "assistant"
        return """
            {
              "role": "$role",
              "content": "$message"
            }
        """.trimIndent()
    }
    fun setRequest(message: String) {
        _chats.value += Chat(message = message, isAssistant = false)
        // adapt the conversation  the chat gpt api understands and add to the request
        var conversation = ""
        // the first chunk is the user message and the assistant response so we need to separate them
        _chats.value.chunked(2).map {
            conversation += if (it.size == 2) {
                ","+message(it[0].message) + "," + message(it[1].message,false) + ","
            } else {
                "," + message(it[0].message)
            }
        }

        val content = "You are a friendly and helpful assistant, skilled in helping people to place" +
                " their order. you don't ask personal address, location or payment information. " +
                "You can Make suggestions and recommendations if the user don't have any idea about what to order." +
                "you can answer any question even if it's not related to the order. " +
                "You know the list of restaurants and their menus. " + menu.value


        val request = """
            {
              "model": "gpt-4",
              "messages": [
                {
                  "role": "system", 
                  "content": "$content"
                }
                ${conversation.replace(",,",",")}
              ],
              "tools": [
                {
                  "type": "function",
                  "function": {
                    "name": "order_food",
                    "description": "Get the current weather in a given location",
                    "parameters": {
                      "type": "object",
                      "properties": {
                        "restaurantName": {
                          "type": "string",
                          "description": "The restaurant name, e.g. Burger King"
                        },
                        "foodName": {
                          "type": "string",
                          "description": "The food name, e.g. cheeseburger"
                        },
                        "foodCategory": {
                          "type": "string",
                          "description": "The food category, e.g. Burger"
                        },
                        "foodQuantity": {
                          "type": "string",
                          "description": "The food quantity, e.g. 2"
                        },
                        "mealId": {
                          "type": "string",
                          "description": "The id of the meal the user selected, e.g. 1"
                        }
                      },
                      "required": ["foodName", "foodCategory", "foodQuantity", "foodPrice"]
                    }
                  }
                }
              ],
              "tool_choice": "auto"
            }
        """.trimIndent()

        val json = Json.decodeFromString<ChatRequest>(request)
        viewModelScope.launch {
            chatUseCase(json).collect {result->
                when(result){
                    is UiEvent.Loading -> {
                        _loading.value = true
                    }
                    is UiEvent.Success -> {
                        _chats.value += result.data!!
                        _loading.value = false
                    }
                    else -> {_loading.value = false}
                }

            }
        }
    }

    fun setMenu(menu: String){
        this.menu.value = menu
    }

}