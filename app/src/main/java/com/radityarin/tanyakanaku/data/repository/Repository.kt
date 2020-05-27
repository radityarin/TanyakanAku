package com.radityarin.tanyakanaku.data.repository

import com.radityarin.tanyakanaku.data.response.AnswerResponse

interface Repository {

    fun getAnswers(
        query: String,
        onResult: (AnswerResponse) -> Unit
    )
}