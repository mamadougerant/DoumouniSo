package com.malisoftware.ai.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malisoftware.ai.chatUseCase.ChatUseCase
import com.malisoftware.ai.model.Chat
import com.malisoftware.ai.model.request.ChatRequest
import com.malisoftware.ai.model.response.ResponseObject
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase,
    private val dataUseCase: DataUseCase
) : ViewModel() {

    private val menu  = mutableStateOf(emptyList<Pair<String,String>>())
    private val restaurantAndItems = mutableStateOf(emptyList<Pair<String,List<Items>>>())

    private val _chats = MutableStateFlow<List<Chat>>(emptyList())
    val chats: StateFlow<List<Chat>> = _chats

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading


    init {
        viewModelScope.launch {
            getRestaurantList()
            getRestaurantsMenu()
        }
    }

    private suspend fun getRestaurantList(){
        dataUseCase.getRestaurantList().collect {result->
            when(result){
                is UiEvent.Loading -> {
                    _loading.value = true
                }
                is UiEvent.Success -> {
                    _loading.value = false
                    menu.value = result.data!!.filter { it.isOpen }.map { Pair(it.title,it.id) }
                }
                else -> {_loading.value = false}
            }
        }
    }

    private suspend fun getRestaurantsMenu(){

        menu.value.forEach {restaurant->
            dataUseCase.getRestaurantItems(restaurant.second).collect { result ->
                when (result) {
                    is UiEvent.Loading -> {
                        _loading.value = true
                    }

                    is UiEvent.Success -> {
                        _loading.value = false
                        val items = result.data!!
                            .map { it.items }.flatten()
                            .map { it.items }.flatten()
                        restaurantAndItems.value += Pair(restaurant.first, items)
                    }

                    else -> {
                        _loading.value = false
                    }
                }
            }
        }
    }


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
        // prevent user to send multiple messages
        if ( _chats.value.isNotEmpty() &&  !_chats.value.last().isAssistant) _chats.value = _chats.value.dropLast(1)
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
                "You know the list of restaurants and their menus. " + restaurantAndItems.value.toString() + ""


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
                        Log.d("ChatViewModel", "setRequest2: ${result.data}")

                        _chats.value += result.data!!.copy(items = findFood(result.data!!.orderInfo))

                        _loading.value = false
                    }
                    else -> {
                        _loading.value = false
                        Log.d("ChatViewModel", "setRequest: ${result.message}")
                    }
                }

            }
        }
    }

    fun findFood (data: ResponseObject? = null,) : List<Items> {
        if (data == null) return emptyList()
        val restaurant = data.restaurantName
        return if (restaurant == "") restaurantAndItems.value.find { it.first == restaurant }?.second?.filter {
            it.title.contains(data.foodName)
        } ?: emptyList() else restaurantAndItems.value.flatMap { it.second }.filter {
            it.title.contains(data.foodName) || it.description.contains(data.foodName) || it.category.contains(data.foodName)
        }
    }


}