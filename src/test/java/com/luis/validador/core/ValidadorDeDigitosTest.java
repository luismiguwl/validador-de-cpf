package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luis.validador.model.*;

public class ValidadorDeDigitosTest {
	ValidadorDeDigitos validador;
	CPF cpf;
	String numerosDoCPF = getCpfValido();
	
	@BeforeEach
	void setUp() {
		cpf = new CPF(numerosDoCPF);
		validador = new ValidadorDeDigitos(cpf);
	}
	
	private String getCpfValido() {
		return "479.885.790-45";
	}
	
	private String getCpfComApenasPrimeiroDigitoValido() {
		return "479.885.790-46";
	}
	
	private String getCpfComApenasSegundoDigitoValido() {
		return "479.885.790-35";
	}
	
	@Test
	void deveRetornarTrueSePrimeiroDigitoForValido() {
		assertThat(validador.validarPrimeiroDigito()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSePrimeiroDigitoForInvalido() {
		numerosDoCPF = getCpfComApenasSegundoDigitoValido();
		setUp();
		assertThat(validador.validarPrimeiroDigito()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeSegundoDigitoForValido() {
		assertThat(validador.validarSegundoDigito()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeSegundoDigitoForInvalido() {
		numerosDoCPF = getCpfComApenasPrimeiroDigitoValido();
		setUp();
		assertThat(validador.validarSegundoDigito()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeDigitoForValido() {
		assertThat(validador.digitoEhValido(359, cpf.getPrimeiroDigitoVerificador())).isTrue();
	}
	
	@Test
	void deveRetornarTrueSeDigitoForValido2() {
		assertThat(validador.digitoEhValido(424, cpf.getSegundoDigitoVerificador())).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeDigitoForInvalido() {
		numerosDoCPF = getCpfComApenasSegundoDigitoValido();
		setUp();
		assertThat(validador.digitoEhValido(359, cpf.getPrimeiroDigitoVerificador())).isFalse();
	}
	
	@Test
	void deveRetornarFalseSeDigitoForInvalido2() {
		numerosDoCPF = getCpfComApenasPrimeiroDigitoValido();
		setUp();
		assertThat(validador.digitoEhValido(424, cpf.getSegundoDigitoVerificador())).isFalse();
	}
	
	@Test
	void deveRetornar359AoVerificarPrimeiroDigito() {
		assertThat(validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(10, 2)).isEqualTo(359);
	}
	
	@Test
	void deveRetornar424AoVerificarSegundoDigito() {
		assertThat(validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(11, 2)).isEqualTo(424);
	}
	
	@Test
	void deveRetornarTrueSeDigitosEstiveremCorretos() {
		assertThat(validador.digitosVerificadoresSaoValidos()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeApenasPrimeiroDigitoForValido() {
		numerosDoCPF = getCpfComApenasPrimeiroDigitoValido();
		setUp();
		assertThat(validador.digitosVerificadoresSaoValidos()).isFalse();
	}
	
	@Test
	void deveRetornarFalseSeApenasSegundoDigitoForValido() {
		numerosDoCPF = getCpfComApenasSegundoDigitoValido();
		setUp();
		assertThat(validador.digitosVerificadoresSaoValidos()).isFalse();
	}
}
