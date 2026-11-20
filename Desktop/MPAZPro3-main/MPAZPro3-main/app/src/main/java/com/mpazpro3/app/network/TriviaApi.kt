package com.mpazpro3.app.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class TriviaResponse(
    val response_code: Int,
    val results: List<TriviaQuestion>
)

data class TriviaQuestion(
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)

interface TriviaApi {
    @GET("api.php")
    fun getTriviaQuestions(
        @Query("amount") amount: Int = 5,
        @Query("type") type: String = "multiple"
    ): Call<TriviaResponse>
}