package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidadorDeEstruturaTest {
	
	ValidadorDeEstrutura validador;
	String cpf;
	
	@BeforeEach
	void setUp() {
		validador = new ValidadorDeEstrutura(cpf);
	}
	
	@Test
	void deveRetornarTrueSeCPFForNulo() {
		assertThat(validador.ehNulo()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoForNulo() {
		cpf = "";
		setUp();
		assertThat(validador.ehNulo()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeCPFConterOnzeNumeros() {
		String numeroQualquer = "1";
		cpf = numeroQualquer.repeat(11);
		setUp();
		assertThat(validador.possuiOnzeNumeros()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoConterOnzeNumeros() {
		String numeroQualquer = "1";
		cpf = numeroQualquer.repeat(10);
		setUp();
		assertThat(validador.possuiOnzeNumeros()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeCPFPossuirTodosCaracteresValidos() {
		cpf = "1.-";
		setUp();
		assertThat(validador.caracteresSaoValidos()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoPossuiTodosCaracteresValidos() {
		cpf = "*";
		setUp();
		assertThat(validador.caracteresSaoValidos()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeNumerosDoCPFSaoIguais() {
		String numeroQualquer = "1";
		cpf = numeroQualquer.repeat(11);
		setUp();
		assertThat(validador.numerosSaoIguais()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeNumerosDoCPFNaoSaoIguais() {
		cpf = "1234";
		setUp();
		assertThat(validador.numerosSaoIguais()).isFalse();
	}
	
	@Test
	void deveRetornarUmSeCPFComecarComUm() {
		cpf = "1234";
		setUp();
		assertThat(validador.obterPrimeiroNumeroDoCPF()).isEqualTo(1);
	}
	
	@Test
	void deveRetornarArrayPreenchidoComDeterminadoNumero() {
		setUp();
		int numero = 7;
		
		String[] arrayRetornado = validador.preencherArrayComPrimeiroNumeroDoCPF(numero);
		String numeroString = Integer.toString(numero);
		String[] arrayEsperado = numeroString.repeat(11).split("");
		
		assertThat(arrayEsperado).isEqualTo(arrayRetornado);
	}
	
	@Test
	void deveLancarExcecaoAoTentarPreencherArrayComNumeroNegativo() {
		setUp();
		assertThatThrownBy(() -> {
			validador.preencherArrayComPrimeiroNumeroDoCPF(-1);
		});
	}
	
	@Test
	void deveRetornarTrueSeNumeroForIgualAoCPF() {
		cpf = "12345678901";
		setUp();
		assertThat(validador.numerosDoCPFSaoIguais("12345678901")).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeNumeroNaoForIgualAoCPF() {
		cpf = "12345678901";
		setUp();
		assertThat(validador.numerosDoCPFSaoIguais("1")).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeEstruturaEstiverOK() {
		cpf = "479.885.790-45";
		setUp();
		assertThat(validador.ehValida()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeCPFForNulo() {
		cpf = null;
		setUp();
		assertThat(validador.ehValida()).isFalse();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoPossuirOnzeNumeros() {
		cpf = "1234";
		setUp();
		assertThat(validador.ehValida()).isFalse();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoPossuirTodosCaracteresValidos() {
		cpf = "479.885.790*45";
		setUp();
		assertThat(validador.ehValida()).isFalse();
	}
}
