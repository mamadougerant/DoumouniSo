package com.malisoftware.ai.repository

import com.malisoftware.ai.remote.GptApi
import com.malisoftware.ai.model.request.ChatRequest
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val gptApi: GptApi,
) {

        suspend fun getChat(
            request: ChatRequest
        ) = gptApi.getGptResponse(
            request = request
        )

}