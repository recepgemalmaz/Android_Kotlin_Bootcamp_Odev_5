package com.recepgemalmaz.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.recepgemalmaz.simplecalculator.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var input: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNumberButtons()
        setupActionButton()
    }

    private fun setupNumberButtons() {
        val numberButtonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )

        for (buttonId in numberButtonIds) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                handleNumberButtonClick(button.text.toString())
            }
        }
    }

    private fun handleNumberButtonClick(number: String) {
        input += number
        binding.textView.text = input
    }

    private fun setupActionButton() {
        binding.buttonAC.setOnClickListener {
            input = ""
            binding.textView.text = input
        }

        binding.buttonDelete.setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                binding.textView.text = input
            }
        }

        binding.buttonPlus.setOnClickListener {
            handlePlusButtonClick()
        }

        binding.buttonEqual.setOnClickListener {
            handleEqualButtonClick()
        }
    }

    private fun handlePlusButtonClick() {
        if (input.isNotEmpty() && input.last() != '+') {
            input += "+"
            binding.textView.text = input
        } else {
            Toast.makeText(this, " Sayi Giriniz ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleEqualButtonClick() {
        if (input.isNotEmpty() && input.last() != '+') {
            val sayilar = input.split("+")
            var toplam = 0
            for (sayi in sayilar) {
                toplam += sayi.toIntOrNull() ?: 0
            }
            binding.textView.text = toplam.toString()
            input = ""
        } else {
            Toast.makeText(this, " Gecersiz Bicim ", Toast.LENGTH_SHORT).show()
        }
    }
}

