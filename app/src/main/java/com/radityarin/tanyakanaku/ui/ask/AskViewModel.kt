package com.radityarin.tanyakanaku.ui.ask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.radityarin.tanyakanaku.data.repository.AppRepository
import com.radityarin.tanyakanaku.data.response.AnswerResponse

class AskViewModel constructor(
    private val repository: AppRepository
) : ViewModel() {

    var answer : MutableLiveData<AnswerResponse> = MutableLiveData()

    fun getAnswer(query: String){
        repository.getAnswers(query) {
            answer.postValue(it)
        }
    }

}