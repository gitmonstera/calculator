package com.example.calc

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import android.view.WindowManager
import android.content.res.Configuration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isRotated = isScreenRotated(this)

        if (isRotated) {
            // Если горизонтально
            setContentView(R.layout.activity_main_horiz)
            var cheker = true
            var checker = true

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

            val exp2: Button = findViewById(R.id.exp2)
            val exp3: Button = findViewById(R.id.exp3)
            val sqrt: Button = findViewById(R.id.sqrt)
            val sin: Button = findViewById(R.id.sin)

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
                try {
                    if(input.text.isNotEmpty() && input.text.last() != '+')
                        SetTextFilds("+")
                    else if(input.text.isEmpty())
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    when(e){
                        is NoSuchElementException -> {  }
                        else -> { Log.d("Main", "ERROR: $e") }
                    }
                }
                checker = true }

            minus.setOnClickListener {
                try {
                    if(input.text.last() != '-')
                        SetTextFilds("-")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    when(e){
                        is NoSuchElementException -> { SetTextFilds("-") }
                        else -> Log.d("Main", "ERROR: $e")
                    }
                }
                checker = true }
            perc.setOnClickListener {
                try {
                    if(input.text.last() != '%')
                        SetTextFilds("%")
                    else
                        SetTextFilds("")
                } catch (e: Exception) {
                    Log.d("Main", "ERROR: $e")
                }
            }
            point.setOnClickListener {
                try {
                    if(cheker) {
                        if(input.text.isNotEmpty() && input.text.last() != '.'){
                            if(input.text.last().toString().matches(Regex("[0-9]")))
                                SetTextFilds(".")
                        } else if(input.text.isEmpty())
                            SetTextFilds("")
                        cheker = false }
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                } }

            div.setOnClickListener {
                try {
                    if(input.text.last() != '/')
                        SetTextFilds("/")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    when (e) {
                        is NoSuchElementException -> {
                            input.text = "0/"
                        }
                        else -> Log.d("Main", "ERROR: $e")
                    }
                }
                checker = true }
            multiply.setOnClickListener {
                try {
                    if(input.text.last() != '*')
                        SetTextFilds("*")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                }
                checker = true }

            mp.setOnClickListener {
                try {
                    val tmp = findLastNumber(input.text.toString()).toString()
                    if (checker){
                        checker = false
                        input.text = input.text.toString().dropLast(tmp.length) + "(-" + tmp + ")"
                    }else{
                        if (input.text.toString().endsWith("(-" + tmp + ")")){
                            input.text = input.text.toString().dropLast(tmp.length + 3) + tmp
                        }
                        checker = true
                    }
                    cheker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                } }
            ac.setOnClickListener {
                try {
                    input.text = ""
                    output.text = ""
                    cheker = true
                    checker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR") }
            }

            exp2.setOnClickListener {
                try {
                    if(input.text.last() != '^')
                        SetTextFilds("^2")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                }
                checker = true }

            exp3.setOnClickListener {
                try {
                    if(input.text.last() != '^')
                        SetTextFilds("^3")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                }
                checker = true }

            sqrt.setOnClickListener {
                try {
                    if(input.text.last() != '√')
                        SetTextFilds("√2")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                }
                checker = true }

            res.setOnClickListener {
                try {
                    val expression = input.text.toString()
                    val result = ExpressionParser.evaluate(expression, "all")
                    output.text = result.toString()
                    if(result.toString() == "Infinity" || result.toString() == "ERROR"){
                        output.text = "ERROR"
                    }
                } catch (e: Exception) {
                    when(e){
                        is IndexOutOfBoundsException -> { Toast.makeText(this, "ERROR ", Toast.LENGTH_LONG).show() }
                        is NumberFormatException -> {
                            try {
                                val expression = "0" + input.text.toString()
                                val result = ExpressionParser.evaluate(expression, "all")
                                output.text = result.toString()
                                if(result.toString() == "Infinity" || result.toString() == "ERROR"){
                                    output.text = "ERROR"
                                }
                            } catch (e:Exception){
                                Toast.makeText(this, "ERROR NumberFormatException", Toast.LENGTH_LONG).show()
                            } }
                        is NoSuchElementException -> { Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show() }
                        else -> {
                            Log.d("Main", "ERROR: $e")
                            Toast.makeText(this, "ERROR $e", Toast.LENGTH_LONG).show() }
                    }
                }
            }

        } else {
            // Если вертикально
            setContentView(R.layout.activity_main)

            var cheker = true
            var checker = true

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
                try {
                    if(input.text.isNotEmpty() && input.text.last() != '+')
                        SetTextFilds("+")
                    else if(input.text.isEmpty())
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    when(e){
                        is NoSuchElementException -> {  }
                        else -> { Log.d("Main", "ERROR: $e") }
                    }
                }
                checker = true
            }

            minus.setOnClickListener {
                try {
                    if(input.text.last() != '-')
                        SetTextFilds("-")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    when(e){
                        is NoSuchElementException -> { SetTextFilds("-") }
                        else -> Log.d("Main", "ERROR: $e")
                    }
                }
                checker = true
            }
            perc.setOnClickListener {
                try {
                    if(input.text.last() != '%')
                        SetTextFilds("%")
                    else
                        SetTextFilds("")
                } catch (e: Exception) {
                    Log.d("Main", "ERROR: $e")
                }
                checker = true
            }
            point.setOnClickListener {
                try {
                    if(cheker) {
                        if(input.text.isNotEmpty() && input.text.last() != '.'){
                            if(input.text.last().toString().matches(Regex("[0-9]")))
                                SetTextFilds(".")
                        } else if(input.text.isEmpty())
                            SetTextFilds("")
                        cheker = false }
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                } }

            div.setOnClickListener {
                try {
                    if(input.text.last() != '/')
                        SetTextFilds("/")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    when (e) {
                        is NoSuchElementException -> {
                            input.text = "0/"
                        }
                        else -> Log.d("Main", "ERROR: $e")
                    }
                }
                checker = true
            }
            multiply.setOnClickListener {
                try {
                    if(input.text.last() != '*')
                        SetTextFilds("*")
                    else
                        SetTextFilds("")
                    cheker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                }
                checker = true
            }


            mp.setOnClickListener {
                try {
                    val tmp = findLastNumber(input.text.toString()).toString()
                    if (checker){
                        checker = false
                        input.text = input.text.toString().dropLast(tmp.length) + "(-" + tmp + ")"
                    }else{
                        if (input.text.toString().endsWith("(-" + tmp + ")")){
                            input.text = input.text.toString().dropLast(tmp.length + 3) + tmp
                        }
                        checker = true
                    }
                    cheker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR")
                } }

           ac.setOnClickListener {
                try {
                    input.text = ""
                    output.text = ""
                    cheker = true
                    checker = true
                } catch (e: Exception) {
                    Log.d("Main", "ERROR") } }

            res.setOnClickListener {
                try {
                    val expression = "0" + input.text.toString().replace("(", "").replace(")", "")
                    val result = ExpressionParser.evaluate(expression, "no")
                    output.text = result.toString()
                    if(result.toString() == "Infinity" || result.toString() == "ERROR"){
                        output.text = "ERROR"
                    }
                } catch (e: Exception) {
                    when(e){
                        is IndexOutOfBoundsException -> { Toast.makeText(this, "ERROR ", Toast.LENGTH_LONG).show() }
                        is NumberFormatException -> {
                            try {
                                val expression = "0" + input.text.toString()
                                val result = ExpressionParser.evaluate(expression, "no")
                                output.text = result.toString()
                                if(result.toString() == "Infinity" || result.toString() == "ERROR"){
                                    output.text = "ERROR"
                                }
                            } catch (e:Exception){
                                Toast.makeText(this, "ERROR NumberFormatException", Toast.LENGTH_LONG).show()
                            } }
                        is NoSuchElementException -> { Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show() }
                        else -> {
                            Log.d("Main", "ERROR: $e")
                            Toast.makeText(this, "ERROR $e", Toast.LENGTH_LONG).show() }
                    }
                }
            }
        }
    }

    fun SetTextFilds(inputStr: String){
        val input: TextView = findViewById(R.id.input)
        input.append(inputStr)
    }

    fun isScreenRotated(context: Context): Boolean {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val rotation = windowManager.defaultDisplay.rotation
        return rotation == android.view.Surface.ROTATION_90 || rotation == android.view.Surface.ROTATION_270
    }

    private fun findLastNumber(expression: String): String? {
        //     val regex = Regex("""-?\d+(\.\d+)?(?![\d.])""")
        val regex = Regex("""(?=\d)(?:\d+(\.\d+)?)\b""")
        val matches = regex.findAll(expression)
        var lastMatch: MatchResult? = null
        for (match in matches) {
            lastMatch = match
        }
        return lastMatch?.value
    }
}