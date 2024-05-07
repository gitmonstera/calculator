package com.example.calc

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val output: TextView = findViewById(R.id.output)
        val input: TextView = findViewById(R.id.input)

        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)

        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)

        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        val btn0: Button = findViewById(R.id.zero)

        val ac: Button = findViewById(R.id.AC)
        val mp: Button = findViewById(R.id.plusManus)
        val perc: Button = findViewById(R.id.percent)
        val plus: Button = findViewById(R.id.plus)
        val minus: Button = findViewById(R.id.minus)
        val res: Button = findViewById(R.id.result)
        val point: Button = findViewById(R.id.point)

        val div: Button = findViewById(R.id.division)
        val multiply: Button = findViewById(R.id.multiply)

        btn0.setOnClickListener { SetTextFilds("0") }
        btn1.setOnClickListener { SetTextFilds("1") }
        btn2.setOnClickListener { SetTextFilds("2") }
        btn3.setOnClickListener { SetTextFilds("3") }
        btn4.setOnClickListener { SetTextFilds("4") }
        btn5.setOnClickListener  {SetTextFilds("5") }
        btn6.setOnClickListener { SetTextFilds("6") }
        btn7.setOnClickListener { SetTextFilds("7") }
        btn8.setOnClickListener { SetTextFilds("8") }
        btn9.setOnClickListener { SetTextFilds("9") }

        plus.setOnClickListener {
            if(input.text.isNotEmpty() && input.text.last() != '+')
                SetTextFilds("+")
            else if(input.text.isEmpty())
                SetTextFilds("") }

        minus.setOnClickListener {
            if(input.text.last() != '-')
                SetTextFilds("-")
            else
                SetTextFilds("") }
        perc.setOnClickListener {
            if(input.text.last() != '%')
                SetTextFilds("%")
            else
                SetTextFilds("") }
        point.setOnClickListener {
            if(input.text.isNotEmpty() && input.text.last() != '.'){
                if(input.text.last().toString().matches(Regex("[0-9]")))
                    SetTextFilds(".")
            } else if(input.text.isEmpty())
                SetTextFilds("")}

        div.setOnClickListener {
            if(input.text.last() != '/')
                SetTextFilds("/")
            else
                SetTextFilds("") }
        multiply.setOnClickListener {
            if(input.text.last() != '*')
                SetTextFilds("*")
            else
                SetTextFilds("") }

        mp.setOnClickListener { SetTextFilds("") }
        ac.setOnClickListener {
            input.text = ""
            output.text = "" }

        res.setOnClickListener {
            try {
                val expression = input.text.toString()
                val result = ExpressionParser.evaluate(expression, this)
                output.text = result.toString()
            }catch (e: Exception) {
                when(e){
                    is IndexOutOfBoundsException -> { Toast.makeText(this, "ERROR X%Y", Toast.LENGTH_LONG).show() }
                    is NumberFormatException -> { Toast.makeText(this, "ERROR NumberFormatException", Toast.LENGTH_LONG).show() }
                    else -> {
                        Log.d("Main", "ERROR: $e")
                        Toast.makeText(this, "ERROR $e", Toast.LENGTH_LONG).show() }
                }
            }

        }

    }

    fun SetTextFilds(inputStr: String){
        val input: TextView = findViewById(R.id.input)
        input.append(inputStr)
    }
}