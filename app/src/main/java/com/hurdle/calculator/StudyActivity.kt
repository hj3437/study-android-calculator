package com.hurdle.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hurdle.calculator.databinding.ActivityStudyBinding
import java.lang.NumberFormatException

/*
* 참고한 영상입니다.
* https://www.youtube.com/playlist?list=PLMocbRXgGcjblqWSMjUfu9mL6GQ-X6a0S
* */
class StudyActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityStudyBinding

    private var operator = "+"
    private var lastOperator = "+"
    private var resultNumber: Double = 0.0
    private var inputNumber: Double = 0.0

    private var isFirstInput = true
    private var isOperator = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init
        binding.mainInputTextView.text = ""
        binding.mainRecordTextView.text = ""
    }

    fun onButtonClick(view: View) {
        val btnTag = view.tag.toString()

        if (btnTag == getString(R.string.btn_plus) ||
            btnTag == getString(R.string.btn_minus) ||
            btnTag == getString(R.string.btn_multiply) ||
            btnTag == getString(R.string.btn_divide)
        ) {
            // +, -, *, /
            operatorEvent(view)
        } else if (btnTag == getString(R.string.btn_one) ||
            btnTag == getString(R.string.btn_two) ||
            btnTag == getString(R.string.btn_three) ||
            btnTag == getString(R.string.btn_four) ||
            btnTag == getString(R.string.btn_five) ||
            btnTag == getString(R.string.btn_six) ||
            btnTag == getString(R.string.btn_seven) ||
            btnTag == getString(R.string.btn_eight) ||
            btnTag == getString(R.string.btn_nine) ||
            btnTag == getString(R.string.btn_zero) ||
            btnTag == getString(R.string.btn_open_parentheses) ||
            btnTag == getString(R.string.btn_close_parentheses)

        ) {
            // 0 ~ 9
            numButtonClick(view)
        } else if (btnTag == getString(R.string.btn_ac)) {
            // AC
            resetEvent()
        } else if (btnTag == getString(R.string.btn_dot)) {
            // .
            dotEvent(view)
        } else if (btnTag == getString(R.string.btn_result)) {
            // =
            resultEvent(view)
        }
    }

    private fun operatorEvent(view: View) {
        isOperator = true
        lastOperator = view.tag.toString()

        if (isFirstInput) {

            if (operator == getString(R.string.btn_result)) {
                operator = view.tag.toString()
                resultNumber = binding.mainInputTextView.text.toString().toDouble()
                binding.mainRecordTextView.text = "$resultNumber $operator "
            } else {
                operator = view.tag.toString()
                val getRecordText = binding.mainRecordTextView.text.toString()
                val substring = getRecordText.substring(0, getRecordText.length - 2)

                binding.mainRecordTextView.text = substring
                binding.mainRecordTextView.append("$operator ")
            }
        } else {
            inputNumber = binding.mainInputTextView.text.toString().toDouble()
            resultNumber = calculateNumber(view, inputNumber, operator)
            binding.mainInputTextView.text = resultNumber.toString()
            isFirstInput = true
            operator = view.tag.toString()
            binding.mainRecordTextView.append("$inputNumber $operator ")
        }
    }

    private fun resetEvent() {
        binding.mainInputTextView.text = ""
        binding.mainRecordTextView.text = ""
        resultNumber = 0.0
        resultNumber = 0.0
        operator = getString(R.string.btn_plus)
        isFirstInput = true
        isOperator = false
    }

    private fun resultEvent(view: View) {
        if (isFirstInput) {
            binding.mainRecordTextView.text = "$resultNumber $lastOperator "
            resultNumber = calculateNumber(view, inputNumber, operator)
            binding.mainInputTextView.text = resultNumber.toString()
        } else {
            inputNumber = binding.mainInputTextView.text.toString().toDouble()
            resultNumber = calculateNumber(view, inputNumber, operator)

            lastOperator = operator

            binding.mainInputTextView.text = resultNumber.toString()

            isFirstInput = true
            operator = view.tag.toString()

            binding.mainRecordTextView.append("$operator ")
        }
    }

    private fun dotEvent(view: View) {
        if (isFirstInput) {
            binding.mainInputTextView.text = "0" + view.tag.toString()
            isFirstInput = false
        } else {
            if (binding.mainInputTextView.text.toString()
                    .contains(getString(R.string.btn_dot))
            ) {
                Toast.makeText(this, "이미 소숫점이 존재", Toast.LENGTH_SHORT).show()
            } else {
                binding.mainInputTextView.append(view.tag.toString())
            }
        }
    }

    // 숫자 버튼 클릭이벤트 처리
    private fun numButtonClick(view: View) {
        if (isFirstInput) {
            binding.mainInputTextView.text = view.tag.toString()
            isFirstInput = false

            if (operator == getString(R.string.btn_result)) {
                // reset
                binding.mainRecordTextView.text = null
            }

        } else {
            if (binding.mainInputTextView.text.toString() == getString(R.string.btn_zero)) {
                Toast.makeText(this, "0은 소수만 가능합니다.", Toast.LENGTH_SHORT).show()
                isFirstInput = true
            } else {
                binding.mainInputTextView.append(view.tag.toString())
            }
        }
    }

    private fun calculateNumber(view: View, inputNumber: Double, operator: String): Double {
        // ***
        // 전역변수를 사용하고 있습니다.
        // 전역변수들이 어디서 어떻게 되는지 관리가 어려워집니다.
        // 윗부분은 지역변수로 나중에 값을 돌려주는 부분은 전역변수로 만듭니다.
        try {
            this.inputNumber = binding.mainInputTextView.text.toString().toDouble()

            when (this.operator) {
                getString(R.string.btn_result) -> {
                    this.resultNumber = inputNumber
                }

                getString(R.string.btn_plus) -> {
                    // Log.d(TAG, "onButtonClick: btn_plus")
                    this.resultNumber += inputNumber
                }
                getString(R.string.btn_minus) -> {
                    // Log.d(TAG, "onButtonClick: btn_minus")
                    this.resultNumber -= inputNumber
                }
                getString(R.string.btn_multiply) -> {
                    // Log.d(TAG, "onButtonClick: btn_multiply")
                    this.resultNumber *= inputNumber
                }
                getString(R.string.btn_divide) -> {
                    // Log.d(TAG, "onButtonClick: btn_divide")
                    this.resultNumber /= inputNumber
                }
            }

            isOperator = true
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        return resultNumber
    }

    fun onBackspaceKey(view: View) {
        if (!isFirstInput) {
            val getResultText = binding.mainInputTextView.text.toString()
            if (getResultText.isNotEmpty()) {
                val substring = getResultText.substring(0, getResultText.length - 1)
                binding.mainInputTextView.text = substring
            }
        }
    }
}