package com.example.calc

import android.content.Context
import android.widget.Toast

object ExpressionParser {

    fun evaluate(expression: String, context: Context): Double {
        val withoutSpaces = expression.replace("\\s+".toRegex(), "")

        // Деление выражения на части
        val parts = mutableListOf<String>()
        var currentNumber = ""
        for (char in withoutSpaces) {
            if (char.isDigit() || char == '.') {
                currentNumber += char
            } else {
                if (currentNumber.isNotEmpty()) {
                    parts.add(currentNumber)
                    currentNumber = ""
                }
                parts.add(char.toString())
            }
        }
        if (currentNumber.isNotEmpty()) {
            parts.add(currentNumber)
        }

        // 1 вычисляем проценты
        var i = 0
        while (i < parts.size) {
            if (parts[i] == "%") {
                val value = parts[i - 1].toDouble()
                val percent =  parts[i + 1].toDouble()
                val result = value * percent / 100
                parts[i - 1] = result.toString()
                parts.removeAt(i)
                parts.removeAt(i)
            } else {
                i++
            }
        }

        // 2 умножение и деление
        i = 0
        while (i < parts.size) {
            if (parts[i] == "*" || parts[i] == "/") {
                val leftOperand = parts[i - 1].toDouble()
                val rightOperand = parts[i + 1].toDouble()
                val result = if (parts[i] == "*") leftOperand * rightOperand else leftOperand / rightOperand
                parts[i - 1] = result.toString()
                parts.removeAt(i)
                parts.removeAt(i)
            } else {
                i++
            }
        }

        // 3 сложение и вычитание
        var result = parts[0].toDouble()
        var currentOperator = ""
        for (i in 1 until parts.size step 2) {
            val operator = parts[i]
            val operand = parts[i + 1].toDouble()
            when (operator) {
                "+" -> result += operand
                "-" -> result -= operand
            }
        }
        return result
    }
}
