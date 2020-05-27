package com.radityarin.tanyakanaku.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Answer(
    @Expose @SerializedName("answer") val answer: String? = null,
    @Expose @SerializedName("answer_type") val answer_type: String? = null,
    @Expose @SerializedName("result_sentence") val result_sentence: String? = null,
    @Expose @SerializedName("key_words") val key_words: List<String>? = null
)