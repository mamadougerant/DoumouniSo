package com.malisoftware.ai.chatUseCase

import com.malisoftware.ai.repository.ChatRepository
import com.malisoftware.ai.model.Chat
import com.malisoftware.ai.model.request.ChatRequest
import com.malisoftware.ai.model.response.ResponseObject
import com.malisoftware.components.uiEvent.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    operator fun invoke(request: ChatRequest) = flow {
        emit(UiEvent.Loading())
        val result = chatRepository.getChat(
            request = request
        )
        val message = result.body()?.choices?.first()?.message?.content
        val toolData = result.body()?.choices?.first()?.message
            ?.tool_calls?.get(0)?.function?.arguments
        if (toolData != null){
            val responseObject = Json.decodeFromString<ResponseObject>(toolData)
            emit(UiEvent.Success(Chat(message = message!!, orderInfo = responseObject, isAssistant = true)))
        }else{
            emit(UiEvent.Success(Chat(message = message!!, isAssistant = true)))
        }

    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)

}