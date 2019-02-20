package com.target.treinamento.banco;

public class Calculadora {
	
	public Double soma(Double valorA, Double valorB) {
		if(valorA == null || valorB == null) {
			throw new IllegalArgumentException("Operação não permitida.");
		}
		
		return valorA + valorB;
	}
	
}
