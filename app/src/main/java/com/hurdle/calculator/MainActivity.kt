package com.hurdle.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.hurdle.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private var isFirstInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init
        binding.mainInputTextView.text = "0"
        binding.mainRecordTextView.text = "0"
    }

    fun onButtonClick(view: View) {
        // val button = findViewById<Button>(view.id)


        if (isFirstInput) {
            binding.mainInputTextView.text = view.tag.toString()
            isFirstInput = false
        } else {
            binding.mainInputTextView.append(view.tag.toString())
        }

        Log.d(TAG, "BTN >>> ${view.tag  }")
    }
}