package com.radityarin.tanyakanaku.ui.ask

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.radityarin.tanyakanaku.R
import com.radityarin.tanyakanaku.data.model.Answer
import com.radityarin.tanyakanaku.databinding.ActivityAskBinding
import com.radityarin.tanyakanaku.util.hide
import com.radityarin.tanyakanaku.util.show
import com.radityarin.tanyakanaku.util.snackbar
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
        observeLiveData()
    }

    private fun initView() {
        with(binding) {
            bottomSheetBehaviour = BottomSheetBehavior.from(layoutBottomSheet)
            btnTanya.setOnClickListener { ask() }
            layoutContent.setOnClickListener { collapseBottomSheet() }
        }
    }

    private fun observeLiveData() {
        viewModel.answer.observe(this, Observer {
            onDataLoaded(it.data)
        })
    }

    private fun onDataLoaded(answer: Answer) {
        progressBar.hide()
        expandBottomSheet()
        tv_answer.text = answer.answer
        tv_answer_type.text = answer.answer_type
        tv_key_words.text = answer.key_words.toString()
        tv_result_sentence.text = answer.result_sentence!!.trim()
    }

    private fun ask() {
        val question: String = edt_input.text.toString()
        if (question.isEmpty().not()) {
            progressBar.show()
            viewModel.getAnswer(question)
        } else {
            layout_content.snackbar(getString(R.string.masukan_pertanyaan))
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
