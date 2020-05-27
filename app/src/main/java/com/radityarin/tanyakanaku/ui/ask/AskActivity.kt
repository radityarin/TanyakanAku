package com.radityarin.tanyakanaku.ui.ask

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.radityarin.tanyakanaku.data.model.Answer
import com.radityarin.tanyakanaku.data.response.AnswerResponse
import com.radityarin.tanyakanaku.databinding.ActivityAskBinding
import kotlinx.android.synthetic.main.activity_ask.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AskActivity : AppCompatActivity() {

    private val viewModel by viewModel<AskViewModel>()
    private lateinit var binding: ActivityAskBinding
    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<ViewGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        with(binding) {
            bottomSheetBehaviour = BottomSheetBehavior.from(layoutBottomSheet)
            btnTanya.setOnClickListener { ask() }
        }

        viewModel.getAnswers()!!.observe(this, Observer<AnswerResponse> { answerResponse ->
            if (answerResponse != null) {
                Log.d("cek observe success", answerResponse.toString())
                onDataLoaded(answerResponse.data)
            }
            Log.d("cek obs fail", answerResponse.toString())
        })

    }

    private fun onDataLoaded(answer: Answer?) {
        if (answer != null) {
            tv_answer.text = answer.answer
            tv_answer_type.text = answer.answer_type
            tv_key_words.text = answer.key_words.toString()
            tv_result_sentence.text = answer.result_sentence
        }
    }

    private fun ask() {
        val question: String = edt_input.text.toString()
        if (question.isEmpty().not()) {
            viewModel.ask(question)
            expandBottomSheet()
        }
    }

    private fun expandBottomSheet() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun collapseBottomSheet() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onBackPressed() {
        if (bottomSheetBehaviour.state == BottomSheetBehavior.STATE_EXPANDED) {
            collapseBottomSheet()
        } else {
            super.onBackPressed()
        }
    }
}
