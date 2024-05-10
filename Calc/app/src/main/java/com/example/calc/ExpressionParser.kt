package com.example.calc

import android.content.Context
import android.widget.Toast

object ExpressionParser {

    fun evaluate(expression: String): Double {
        val withoutSpaces = expression.replace("\\s+".toRegex(), "")

        // Деление выражения на части
        val parts = mutableListOf<String>()
        var currentNumber = ""
        var lastCharWasOperator = false
        for (char in withoutSpaces) {
            if (char.isDigit() || char == '.') {
                currentNumber += char
                lastCharWasOperator = false
            } else {
                if (char == '-' && lastCharWasOperator) {
                    currentNumber += char
                } else {
                    if (currentNumber.isNotEmpty()) {
                        parts.add(currentNumber)
                        currentNumber = ""
                    }
                    parts.add(char.toString())
                    lastCharWasOperator = true
                }
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

        var result = parts[0].toDouble()
        i = 0
        while (i < parts.size){
            if (parts[i] == "+" || parts[i] == "-"){
                val leftOperand = parts[i - 1].toDouble()
                val rightOperand = parts[i + 1].toDouble()
                result = if (parts[i] == "+") leftOperand + rightOperand else leftOperand - rightOperand
                parts[i - 1] = result.toString()
                parts.removeAt(i)
                parts.removeAt(i)
            } else {
                i++
            }
        }

//        if (type == "all"){
//            i = 0
//            while (i < parts.size){
//                if (parts[i] == "f" || parts[i] == "f"){
//                    val leftOperand = parts[i - 1].toDouble()
//                    val rightOperand = parts[i + 1].toDouble()
//                    result = if (parts[i] == "+") leftOperand + rightOperand else leftOperand - rightOperand
//                    parts[i - 1] = result.toString()
//                    parts.removeAt(i)
//                    parts.removeAt(i)
//                } else {
//                    i++
//                }
//            }
//        }

        return result
    }
}