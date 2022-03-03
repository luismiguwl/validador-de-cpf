package com.luis.validador.model;

public enum DigitoVerificador {
	PRIMEIRO(10), SEGUNDO(11);

	private int quantidadeDeMultiplicacoes;
	private final int NUMERO_MINIMO = 2;

	private DigitoVerificador(int quantidadeDeMultiplicacoes) {
		this.quantidadeDeMultiplicacoes = quantidadeDeMultiplicacoes;
	}

	public int getQuantidadeDeMultiplicacoes() {
		return quantidadeDeMultiplicacoes;
	}

	public int getNumeroMinimo() {
		return NUMERO_MINIMO;
	}

	public static boolean ehValido(int multiplicacao, int digito) {
		return (multiplicacao * 10) % 11 == digito;
	}
}