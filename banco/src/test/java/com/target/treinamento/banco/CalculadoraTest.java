package com.target.treinamento.banco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CalculadoraTest 
{
	@Test
	public void testSomaDoisNumerosEIgualASete() {
		double valorA = 2;
		double valorB = 5;
		
		Calculadora calculadora = new Calculadora();
		Double soma = calculadora.soma(valorA, valorB);
		
		assertEquals(Double.valueOf(7.0), soma);
	}
	
	@Test
	public void testSomaDoisNumerosEIgualDez() {
		double valorA = 5;
		double valorB = 5;
		
		Calculadora calculadora = new Calculadora();
		Double soma = calculadora.soma(valorA, valorB);
		
		assertEquals(Double.valueOf(10), soma);
	}
	
	@Test
	public void testSomaDoisNumerosComDoubles() {
		double valorA = 5.0;
		double valorB = 2.0;
		
		Calculadora calculadora = new Calculadora();
		Double soma = calculadora.soma(valorA, valorB);
		
		assertEquals(Double.valueOf(7.0), soma);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSomaDoisNumerosComPrimeiroParametroInvalido() {
		double valorB = 2.0;
		
		Calculadora calculadora = new Calculadora();
		calculadora.soma(null, valorB);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSomaDoisNumerosComSegundoParametroInvalido() {
		double valorA = 2.0;
		
		Calculadora calculadora = new Calculadora();
		calculadora.soma(valorA, null);
	}
}
