package com.radityarin.tanyakanaku.data.repository

import com.radityarin.tanyakanaku.data.ApiObserver
import com.radityarin.tanyakanaku.data.response.AnswerResponse
import com.radityarin.tanyakanaku.data.source.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AppRepository(private val api: Api) : Repository {
    private val compositeDisposable = CompositeDisposable()

    override fun getAnswers(
        query: String,
        onResult: (AnswerResponse) -> Unit
    ) {
        api.getAnswers(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<AnswerResponse>(compositeDisposable) {
                override fun onApiSuccess(data: AnswerResponse) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

}