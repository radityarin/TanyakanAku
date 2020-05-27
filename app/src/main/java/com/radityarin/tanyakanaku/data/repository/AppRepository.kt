package com.radityarin.tanyakanaku.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.radityarin.tanyakanaku.data.response.AnswerResponse
import com.radityarin.tanyakanaku.data.source.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository(private val api: Api) : Repository {

    override suspend fun getAnswers(query: String): MutableLiveData<AnswerResponse> {

        val data: MutableLiveData<AnswerResponse> = MutableLiveData()

        api.getAnswers(query).enqueue(object : Callback<AnswerResponse> {

            override fun onResponse(
                call: Call<AnswerResponse>?,
                response: Response<AnswerResponse>?
            ) {

                data.value = response!!.body()!!
                Log.d("cek onreponse", data.value.toString())

            }

            override fun onFailure(call: Call<AnswerResponse>?, t: Throwable?) {
                Log.d("cek", "call failed")
            }
        })

        Log.d("cek diluar enqueue", data.value.toString())

        return data
    }
}