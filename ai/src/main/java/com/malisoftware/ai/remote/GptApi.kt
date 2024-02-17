package com.malisoftware.ai.remote

import com.malisoftware.ai.model.request.ChatRequest
import com.malisoftware.ai.model.response.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptApi {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer $OPEN_AI_API_KEY"
    )
    @POST("chat/completions")
    suspend fun getGptResponse(
        @Body request: ChatRequest
    ): Response<ChatResponse>

    companion object {
        const val BASE_URL = "https://api.openai.com/v1/"
        const val OPEN_AI_API_KEY = "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"
    }
}