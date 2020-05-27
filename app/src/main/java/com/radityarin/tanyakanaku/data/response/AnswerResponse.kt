package com.radityarin.tanyakanaku.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.radityarin.tanyakanaku.data.model.Answer

data class AnswerResponse(
    @Expose @SerializedName("success") val success: Boolean? = null,
    @Expose @SerializedName("message") val message: String? = null,
    @Expose @SerializedName("data") val data: Answer? = null
)