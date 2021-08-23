package com.hurdle.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hurdle.calculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private var operator = "+"
    private var resultNumber: Double = 0.0
    private var isFirstInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
            try {
                val inputNumber = binding.mainInputTextView.text.toString().toDouble()

                when (operator) {
                    getString(R.string.btn_plus) -> {
                        Log.d(TAG, "onButtonClick: btn_plus")
                        resultNumber += inputNumber
                    }
                    getString(R.string.btn_minus) -> {
                        Log.d(TAG, "onButtonClick: btn_minus")
                        resultNumber -= inputNumber
                    }
                    getString(R.string.btn_multiply) -> {
                        Log.d(TAG, "onButtonClick: btn_multiply")
                        resultNumber *= inputNumber
                    }
                    getString(R.string.btn_divide) -> {
                        Log.d(TAG, "onButtonClick: btn_divide")
                        resultNumber /= inputNumber
                    }
                }

                binding.mainInputTextView.text = resultNumber.toString()
                isFirstInput = true
                operator = view.tag.toString()

                binding.mainRecordTextView.append("$inputNumber $operator ")

            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }

            Log.d(TAG, "$resultNumber")

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
            btnTag == getString(R.string.btn_close_parentheses) ||
            btnTag == getString(R.string.btn_dot)
        ) {

            if (isFirstInput) {
                binding.mainInputTextView.text = view.tag.toString()
                isFirstInput = false
            } else {
                binding.mainInputTextView.append(view.tag.toString())
            }
        }
        // Log.d(TAG, "BTN >>> ${view.tag}")
    }
}