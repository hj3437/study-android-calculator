package com.hurdle.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hurdle.calculator.databinding.ActivityCalculatorDecimalBinding
import com.hurdle.calculator.databinding.ActivityStudyBinding

class CalculatorDecimalActivity : AppCompatActivity() {
    private val TAG = CalculatorDecimalActivity::class.java.simpleName

    private lateinit var binding: ActivityCalculatorDecimalBinding

    private var isFirstInput = true
    private var resultNumber: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorDecimalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 최대 길이 설정이 필요합니다.
    // 정수형 계산기이므로 0부터 입력하는 방법을 예외처리 해야합니다.
    fun numberClick(view: View) {
        Log.d(TAG, "numberClick: ${view.tag}")
        if (isFirstInput) {
            binding.calculatorInputTextView.text = view.tag.toString()
            isFirstInput = false
        } else {
            binding.calculatorInputTextView.append(view.tag.toString())
        }
    }

    //
    fun operateEvent(view: View) {
        resultNumber = binding.calculatorInputTextView.text.toString().toLong()

        Log.d(TAG, "operateEvent: ${view.tag}, resultNumber: ${resultNumber}")


        when (view.tag.toString()) {

        }
    }

    fun resultEvent(view: View) {
        Log.d(TAG, "resultEvent: ${view.tag}")
    }

    fun clearEvent(view: View) {
        Log.d(TAG, "clearEvent: ${view.tag}")

        binding.calculatorInputTextView.text = "0"
        binding.calculatorRecordTextView.text = null
        isFirstInput = true
        resultNumber = 0L
    }
}