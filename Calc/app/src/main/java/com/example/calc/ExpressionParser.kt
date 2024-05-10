package com.example.calc

import android.content.Context
import android.widget.Toast
import kotlin.math.sin
import kotlin.math.sqrt

object ExpressionParser {

    fun evaluate(expression: String, type: String): Double {
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

        if (type == "all"){
            i = 0
            while (i < parts.size){
                if (parts[i] == "^"){
                    val leftOperand = parts[i - 1].toDouble()
                    val rightOperand = parts[i + 1].toDouble()
                    when (rightOperand){
                        2.0 -> {
                            val result = leftOperand * rightOperand
                            parts[i - 1] = result.toString()
                            parts.removeAt(i)
                            parts.removeAt(i)
                        }
                        3.0 -> {
                            var result = leftOperand
                            for(tmp in 1 downTo 0 step 1){
                                result = result * leftOperand
                            }
                            parts[i - 1] = result.toString()
                            parts.removeAt(i)
                            parts.removeAt(i)
                        }
                        else -> {  }
                    }
                } else {
                    i++
                }
            }

            i = 0
            while (i < parts.size){
                if (parts[i] == "√"){
                    val leftOperand = parts[i - 1].toDouble()
                    val rightOperand = parts[i + 1].toDouble()
                    when (rightOperand){
                        2.0 -> {
                            val result = sqrt(leftOperand)
                            parts[i - 1] = result.toString()
                            parts.removeAt(i)
                            parts.removeAt(i)
                        }
                        3.0 -> {
                            var result = sqrt(leftOperand)
                            parts[i - 1] = result.toString()
                            parts.removeAt(i)
                            parts.removeAt(i)
                        }
                        else -> {  }
                    }
                } else {
                    i++
                }
            }

            i = 0
            while (i < parts.size){
                if (parts[i] == "s"){
                    val rightOperand = parts[i + 1].toDouble()
                    var result = sin(rightOperand)
                    parts[i - 1] = result.toString()
                    parts.removeAt(i + 1) // Remove rightOperand first to avoid index shift
                    parts[i] = ""
                } else {
                    i++
                }
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

        return result
    }
}