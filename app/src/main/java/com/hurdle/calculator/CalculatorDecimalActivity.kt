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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorDecimalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun numberClick(view: View) {
        Log.d(TAG, "numberClick: ${view.tag}")
    }

    fun operateEvent(view: View) {
        Log.d(TAG, "operateEvent: ${view.tag}")
    }

    fun resultEvent(view: View) {
        Log.d(TAG, "resultEvent: ${view.tag}")
    }

    fun clearEvent(view: View) {
        Log.d(TAG, "clearEvent: ${view.tag}")
    }
}