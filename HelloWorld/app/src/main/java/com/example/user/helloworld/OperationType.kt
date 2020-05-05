package com.example.user.helloworld

enum class OperationType(val code: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    SQUARE("√"),
    PERCENTAGE("%"),
    NONE("")
    ;

    companion object {
        fun getOperationTypeByCode(code: String) = values().firstOrNull { it.code == code } ?: NONE
    }
}