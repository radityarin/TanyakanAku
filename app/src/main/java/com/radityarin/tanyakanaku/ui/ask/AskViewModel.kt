package com.radityarin.tanyakanaku.ui.ask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radityarin.tanyakanaku.data.repository.AppRepository
import com.radityarin.tanyakanaku.data.response.AnswerResponse
import kotlinx.coroutines.launch

class AskViewModel constructor(
    private val repository: AppRepository
) : ViewModel() {

    private var response: MutableLiveData<AnswerResponse>

    init {
        response = MutableLiveData()
    }

    fun ask(query: String) {
        if (query.isEmpty().not()) {
            viewModelScope.launch {
                runCatching {
                    response = repository.getAnswers(query)
                }.onSuccess {
                    Log.d("cek", response.toString())
                }.onFailure {
                    Log.d("cek", "failed")
                }
                response = repository.getAnswers(query)
            }
        }
    }

    fun getAnswers(): LiveData<AnswerResponse>? {
        return response
    }

}