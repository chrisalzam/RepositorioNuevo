package com.example.user.conceptos

import kotlin.math.pow
import kotlin.math.sqrt

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

    fun calcularAreaIsosceles(b: Double, a: Double): Double {
        val result = (b * sqrt(a.pow(2) - b.pow(2) / 4)) / 2
        return result
    }

    fun calcularAreaEscaleno(a: Double, b: Double, c: Double): Double {
        val s = (a + b + c) / 2
        val result = sqrt(s * (s - a) * (s - b) * (s - c))
        return result
    }

    fun calcularAreaEquilatero(a: Double): Double {
        val result = (sqrt(3.0) / 4.0) * a.pow(2)
        return result
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
        Forma.RECTANGULO -> calcularAreaCuadradoORectangulo(base, altura)
        Forma.TRIANGULO -> calcularAreaTriangulo(base, altura)
        Forma.NINGUNO -> 0.0
    }

    fun identificarForma(l1: Double, l2: Double, l3: Double, r: Double): Forma {
        return when {
            (l1 != 0.0 && l2 != 0.0 && l3 == 0.0 && r == 0.0 && l1 == l2)
                    || (l1 != 0.0 && l3 != 0.0 && l2 == 0.0 && r == 0.0 && l1 == l3)
                    || (l3 != 0.0 && l2 != 0.0 && l1 == 0.0 && r == 0.0 && l3 == l2) -> Forma.CUADRADO
            (l1 != 0.0 && l2 != 0.0 && l3 == 0.0 && r == 0.0 && l1 != l2)
                    || (l1 != 0.0 && l3 != 0.0 && l2 == 0.0 && r == 0.0 && l1 != l3)
                    || (l3 != 0.0 && l2 != 0.0 && l1 == 0.0 && r == 0.0 && l3 != l2) -> Forma.RECTANGULO
            (l1 != 0.0 && l2 != 0.0 && l3 != 0.0 && r == 0.0) -> Forma.TRIANGULO
            (l1 == 0.0 && l2 == 0.0 && l3 == 0.0 && r != 0.0) -> Forma.CIRCULO
            else -> Forma.NINGUNO
        }
    }

    fun identificarTipoTriangulo(lado1: Double, lado2: Double, lado3: Double): TrianguloTipo {
        return when {
            lado1 == lado2 && lado2 == lado3 -> TrianguloTipo.EQUILATERO
            (lado1 == lado2 && lado2 != lado3) || (lado1 == lado3 && lado3 != lado2) || (lado2 == lado3 && lado1 != lado2) -> TrianguloTipo.ISOSCELES
            else -> TrianguloTipo.ESCALENO
        }
        // if lleva llaves para mas de una linea
    }

    fun identificarTamaño(area: Double): String {
        return when (area) {
            40.0 -> "Grande"
            in 10.0..39.9 -> "Mediano"
            else -> "Chico"
        }
    }

//    private fun rangos() {
//        //for (i in 1..10)
//        //for (i in 1 until 10)
//        for (i in 10 downTo 1) {
//            val mensaje = "Numero: $i"
//
//            if (mensaje.isNotEmpty()) {
//
//            }
//        }
//    }

    fun calcularAreaConLados(
        forma: Forma,
        tipo: TrianguloTipo,
        lado1: Double = 0.0,
        lado2: Double = 0.0,
        radio: Double = 0.0,
        lado3: Double = 0.0
    ): Double =
        when (forma) {//SI LA FORMA ES CUADRADO SE CALCULA SU AREA LXL ANALIZANDO SUS 3 LADOS
            Forma.CUADRADO -> when {
                lado1 != 0.0 && lado2 != 0.0 && lado3 == 0.0 && radio == 0.0 && lado1 == lado2 -> calcularAreaCuadradoORectangulo(
                    lado1,
                    lado2
                )
                lado1 != 0.0 && lado3 != 0.0 && lado2 == 0.0 && radio == 0.0 && lado1 == lado3 -> calcularAreaCuadradoORectangulo(
                    lado1,
                    lado3
                )
                lado3 != 0.0 && lado2 != 0.0 && lado1 == 0.0 && radio == 0.0 && lado3 == lado2 -> calcularAreaCuadradoORectangulo(
                    lado3,
                    lado2
                )
                else -> 0.0
            }
            Forma.RECTANGULO -> when {
                lado1 != 0.0 && lado2 != 0.0 && lado3 == 0.0 && radio == 0.0 && lado1 != lado2 -> calcularAreaCuadradoORectangulo(
                    lado1,
                    lado2
                )
                lado1 != 0.0 && lado3 != 0.0 && lado2 == 0.0 && radio == 0.0 && lado1 != lado3 -> calcularAreaCuadradoORectangulo(
                    lado1,
                    lado3
                )
                lado3 != 0.0 && lado2 != 0.0 && lado1 == 0.0 && radio == 0.0 && lado3 != lado2 -> calcularAreaCuadradoORectangulo(
                    lado3,
                    lado2
                )
                else -> 0.00
            }
            Forma.CIRCULO -> calcularAreaCirculo(radio)
            Forma.TRIANGULO -> when (tipo) {
                TrianguloTipo.EQUILATERO -> calcularAreaEquilatero(lado1)
                TrianguloTipo.ISOSCELES ->
                    when {
                        lado1 == 0.0 -> calcularAreaIsosceles(lado2, lado3)
                        lado2 == 0.0 -> calcularAreaIsosceles(lado1, lado3)
                        else -> calcularAreaIsosceles(lado1, lado2)
                    }
                TrianguloTipo.ESCALENO -> calcularAreaEscaleno(lado1, lado2, lado3)

            }
            Forma.NINGUNO -> 0.0
        }

    fun mensajeTipoyTamaño(lado1: Double, lado2: Double, lado3: Double, radio: Double): String {
        //El cuadrado/rectangulo/circulo/triangulo es grande/chico/mediano

        val forma = identificarForma(lado1, lado2, lado3, radio)
        val tipo = identificarTipoTriangulo(lado1, lado2, lado3)
        val area = calcularAreaConLados(forma, tipo, lado1, lado2, radio, lado3)
        val size = identificarTamaño(area)
        return if (forma != Forma.TRIANGULO) {
            "La figura es un $forma y es $size"
        } else {
            "La figura es un TRIANGULO $tipo yes $size"
        }
    }

}



