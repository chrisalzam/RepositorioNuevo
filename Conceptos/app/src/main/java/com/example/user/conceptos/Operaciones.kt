package com.example.user.conceptos

import kotlin.math.pow

const val pi = 3.1416

class Operaciones {

    fun calcularAreaCuadradoORectangulo(base: Double, altura: Double): Double {
        val resultado = base * altura
        return resultado
    }


    fun calcularAreaTriangulo(base: Double, altura: Double): Double {
        val resultado = base * altura / 2
        return resultado
    }

    fun calcularAreaCirculo(radio: Double): Double {
        val resultado = pi * radio.pow(2)
        return resultado
    }

    fun esCuadrado(altura: Double, base: Double): Boolean = (base == altura)

    fun calcularArea(
        forma: Forma,
        base: Double = 0.0,
        altura: Double = 0.0,
        radio: Double = 0.0,
        lado: Double = 0.0
    ): Double = when (forma) {
        Forma.CUADRADO -> calcularAreaCuadradoORectangulo(lado, lado)
        Forma.CIRCULO -> calcularAreaCirculo(radio)
        Forma.TRIANGULO -> calcularAreaTriangulo(base, altura)
        Forma.RECTANGULO -> calcularAreaCuadradoORectangulo(base, altura)
    }

    fun identificarTipoTriangulo(lado1: Double, lado2: Double, lado3: Double): TrianguloTipo {
        return when {
            lado1 == lado2 && lado2 == lado3 -> TrianguloTipo.EQUILATERO
            (lado1 == lado2 && lado2 != lado3) || (lado1 == lado3 && lado3 != lado2) || (lado2 == lado3 && lado1 != lado2) -> TrianguloTipo.ISOSCELES
            else -> TrianguloTipo.ESCALENO
        }
    }


}