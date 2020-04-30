package com.example.user.conceptos

import org.junit.Test

import org.junit.Assert.*

class OperacionesTest {

    @Test
    fun calcularAreaCuadradoORectangulo() {
        val vct = Operaciones()
        val area = vct.calcularAreaCuadradoORectangulo(altura = 2.84, base = 5.47)
        assertTrue(area == 15.534799999999999)
    }

    @Test
    fun calcularAreaCuadradoORectangulo2() {
        val vct = Operaciones()
        val area = vct.calcularAreaCuadradoORectangulo(altura = 5.0, base = 2.0)
        assertTrue(area == 10.0)
    }

    @Test
    fun calcularAreaTriangulo() {
        val vct = Operaciones()
        val area = vct.calcularAreaTriangulo(altura = 2.5, base = 2.5)
        assertTrue(area == 3.125)
    }

    @Test
    fun calcularAreaTriangulo2() {
        val vct = Operaciones()
        val area = vct.calcularAreaTriangulo(altura = 10.0, base = 2.0)
        assertTrue(area == 10.0)
    }

    @Test
    fun calcularAreaCirculo() {
        val vct = Operaciones()
        val area = vct.calcularAreaCirculo(radio = 5.0)
        assertTrue(area == 78.53999999999999)
    }

    @Test
    fun calcularAreaCirculo2() {
        val vct = Operaciones()
        val area = vct.calcularAreaCirculo(radio = 1.5957672558078226296300670420988)
        assertTrue(area == 8.0)
    }

    @Test
    fun esCuadrado1() {
        val vct = Operaciones()
        val esCuadrado = vct.esCuadrado(5.0, 5.0)
        assertTrue(esCuadrado)
    }

    @Test
    fun esCuadrado2() {
        val vct = Operaciones()
        val esCuadrado = vct.esCuadrado(10.0, 15.0)
        assertFalse(esCuadrado)
    }

    @Test
    fun calcularArea1() {
        val vct = Operaciones()
        val area = vct.calcularArea(forma = Forma.RECTANGULO, base = 5.0, altura = 15.0)
        assertTrue(area == 75.0)
    }
    @Test
    fun calcularArea2() {
        val vct = Operaciones()
        val area = vct.calcularArea(forma = Forma.CUADRADO, lado = 5.0)
        assertTrue(area == 25.0)
    }
    @Test
    fun calcularArea3() {
        val vct = Operaciones()
        val area = vct.calcularArea(forma = Forma.CIRCULO, radio = 3.0)
        assertTrue(area == 28.2744)
    }
    @Test
    fun calcularArea4() {
        val vct = Operaciones()
        val area = vct.calcularArea(forma = Forma.TRIANGULO, base = 5.0, altura = 2.0)
        assertTrue(area == 5.0)
    }
    @Test
    fun identificarEscaleno() {
        val vct = Operaciones()
        val tipo = vct.identificarTipoTriangulo(2.0,2.0,4.0)
        assertFalse(tipo == TrianguloTipo.ESCALENO)
        val tipo2 = vct.identificarTipoTriangulo(2.0,4.0,4.0)
        assertFalse(tipo2 == TrianguloTipo.ESCALENO)
        val tipo3 = vct.identificarTipoTriangulo(4.0,5.0,4.0)
        assertFalse(tipo3 == TrianguloTipo.ESCALENO)
        val tipo4 = vct.identificarTipoTriangulo(4.0,5.0,6.0)
        assertTrue(tipo4 == TrianguloTipo.ESCALENO)
        val tipo5 = vct.identificarTipoTriangulo(4.0,4.0,4.0)
        assertFalse(tipo5 == TrianguloTipo.ESCALENO)
    }
    // Hola
    @Test
    fun identificarIsosceles() {
        val vct = Operaciones()
        val tipo = vct.identificarTipoTriangulo(2.0,2.0,4.0)
        assertTrue(tipo == TrianguloTipo.ISOSCELES)
        val tipo2 = vct.identificarTipoTriangulo(2.0,4.0,4.0)
        assertTrue(tipo2 == TrianguloTipo.ISOSCELES)
        val tipo3 = vct.identificarTipoTriangulo(4.0,5.0,4.0)
        assertTrue(tipo3 == TrianguloTipo.ISOSCELES)
        val tipo4 = vct.identificarTipoTriangulo(4.0,5.0,6.0)
        assertFalse(tipo4 == TrianguloTipo.ISOSCELES)
        val tipo5 = vct.identificarTipoTriangulo(4.0,4.0,4.0)
        assertFalse(tipo5 == TrianguloTipo.ISOSCELES)
    }
    @Test
    fun identificarEquilatero() {
        val vct = Operaciones()
        val tipo = vct.identificarTipoTriangulo(2.0,2.0,4.0)
        assertFalse(tipo == TrianguloTipo.EQUILATERO)
        val tipo2 = vct.identificarTipoTriangulo(2.0,4.0,4.0)
        assertFalse(tipo2 == TrianguloTipo.EQUILATERO)
        val tipo3 = vct.identificarTipoTriangulo(4.0,5.0,4.0)
        assertFalse(tipo3 == TrianguloTipo.EQUILATERO)
        val tipo4 = vct.identificarTipoTriangulo(4.0,5.0,6.0)
        assertFalse(tipo4 == TrianguloTipo.EQUILATERO)
        val tipo5 = vct.identificarTipoTriangulo(4.0,4.0,4.0)
        assertTrue(tipo5 == TrianguloTipo.EQUILATERO)
    }



}