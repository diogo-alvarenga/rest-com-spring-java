package br.com.diogo_alvarenga.Service;

import br.com.diogo_alvarenga.Exception.UnsupportedMathOperationException;

public class MathService {

	public Double soma(String numberOne, String numberTwo) {
		return convertToDouble(numberOne)+convertToDouble(numberTwo);
	}
	
	public Double subtracao(String numberOne, String numberTwo) {
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	public Double multiplicacao(String numberOne, String numberTwo) {
		return convertToDouble(numberOne)* convertToDouble(numberTwo);
	}
	
	public Double divisao(String numberOne, String numberTwo) {
		return convertToDouble(numberOne)/ convertToDouble(numberTwo);
	}
	
	public Double media(String numberOne, String numberTwo) {
		return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2;
	}
	
	public Double raizQuadrada(String number) {
		return Math.sqrt(convertToDouble(number));
	}
	
	private Double convertToDouble(String strnumber) throws IllegalArgumentException{
		if(strnumber ==null || strnumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value!");
		String number = strnumber.replace(",", ".");
		return Double.parseDouble(number);
	}

	public boolean isNumeric(String strnumber) {
		if(strnumber == null || strnumber.isEmpty()) return false;
		String number = strnumber.replace(",", "."); //Relembrando, replace esta trocando virgula por ponto 
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
