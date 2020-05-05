package com.example.user.helloworld

import kotlin.math.sqrt

class Calculator {
    var firstValue: String = ""
    var secondValue: String = ""
    var currentOperation: OperationType = OperationType.NONE

    fun doOperation(code: String): String {
        if (firstValue.isEmpty()) {
            firstValue = secondValue
            secondValue = ""
        }

        if (secondValue.isNotEmpty()) {
            val first = if (firstValue.isEmpty()) 0.0 else firstValue.toDouble()
            val second = secondValue.toDouble()

            firstValue = when (currentOperation) {
                OperationType.ADD -> second + first
                OperationType.SUBTRACT -> second - first
                OperationType.MULTIPLY -> second * first
                OperationType.DIVIDE -> second / first
                OperationType.SQUARE -> sqrt(second)
                OperationType.PERCENTAGE -> second / 100
                OperationType.NONE -> first
            }.toString()
        }

        currentOperation = OperationType.getOperationTypeByCode(code)

        secondValue = firstValue
        firstValue = ""

        return secondValue
    }
}