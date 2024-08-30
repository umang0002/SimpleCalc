package com.example.simplecalc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecalc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Number listeners
        binding.btn00.setOnClickListener { appendOnClick(true, "00") }
        binding.btn0.setOnClickListener { appendOnClick(true, "0") }
        binding.btn1.setOnClickListener { appendOnClick(true, "1") }
        binding.btn2.setOnClickListener { appendOnClick(true, "2") }
        binding.btn3.setOnClickListener { appendOnClick(true, "3") }
        binding.btn4.setOnClickListener { appendOnClick(true, "4") }
        binding.btn5.setOnClickListener { appendOnClick(true, "5") }
        binding.btn6.setOnClickListener { appendOnClick(true, "6") }
        binding.btn7.setOnClickListener { appendOnClick(true, "7") }
        binding.btn8.setOnClickListener { appendOnClick(true, "8") }
        binding.btn9.setOnClickListener { appendOnClick(true, "9") }

        //Operators Listeners
        binding.btnPlus.setOnClickListener { appendOnClick(false, "+") }
        binding.btnMinus.setOnClickListener { appendOnClick(false, "-") }
        binding.btnMultiply.setOnClickListener { appendOnClick(false, "*") }
        binding.btnDivide.setOnClickListener { appendOnClick(false, "/") }
        binding.btnLeftB.setOnClickListener { appendOnClick(false, "(") }
        binding.btnRightB.setOnClickListener { appendOnClick(false, ")") }

        binding.btnClear.setOnClickListener {
            clear()
        }

        binding.btnEqual.setOnClickListener {
            calculate()
        }

    }

    private fun appendOnClick(clear: Boolean, string: String) {

        if (clear) {
            binding.tvOutPut.text = ""
            binding.tvInput.append(string)
        } else {
            binding.tvInput.append(binding.tvOutPut.text)
            binding.tvInput.append(string)
            binding.tvOutPut.text = ""
        }
    }

    private fun clear() {
        binding.tvInput.text = ""
        binding.tvOutPut.text = ""
    }

    private fun calculate() {
        try {
            val input = ExpressionBuilder(binding.tvInput.text.toString()).build()
            val output = input.evaluate()
            val longOutPut = output.toLong()
            if (output == longOutPut.toDouble()) {
                binding.tvOutPut.text = longOutPut.toString()
            } else {
                binding.tvOutPut.text = output.toString()
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }



}