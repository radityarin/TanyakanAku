package com.radityarin.tanyakanaku.data.repository

import androidx.lifecycle.MutableLiveData
import com.radityarin.tanyakanaku.data.response.AnswerResponse

interface Repository {

    suspend fun getAnswers(query: String): MutableLiveData<AnswerResponse>

}