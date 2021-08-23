package com.hurdle.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hurdle.calculator.databinding.ActivityCalculatorDecimalBinding
import com.hurdle.calculator.databinding.ActivityStudyBinding

class CalculatorDecimalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorDecimalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorDecimalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}