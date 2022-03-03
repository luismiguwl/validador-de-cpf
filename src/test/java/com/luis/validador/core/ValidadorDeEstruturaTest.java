package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidadorDeEstruturaTest {
	
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
		validador.setCpf("");
		assertThat(validador.ehNulo()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeCPFConterOnzeNumeros() {
		validador.setCpf("1".repeat(11));
		assertThat(validador.possuiOnzeNumeros()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoConterOnzeNumeros() {
		validador.setCpf("1".repeat(10));
		assertThat(validador.possuiOnzeNumeros()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeCPFPossuirTodosCaracteresValidos() {
		validador.setCpf("1.-");
		assertThat(validador.caracteresSaoValidos()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoPossuiTodosCaracteresValidos() {
		validador.setCpf("*");
		assertThat(validador.caracteresSaoValidos()).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeNumerosDoCPFSaoIguais() {
		validador.setCpf("1".repeat(11));
		assertThat(validador.numerosSaoIguais()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeNumerosDoCPFNaoSaoIguais() {
		validador.setCpf("1234");
		assertThat(validador.numerosSaoIguais()).isFalse();
	}
	
	@Test
	void deveRetornarUmSeCPFComecarComUm() {
		validador.setCpf("1234");
		assertThat(validador.obterPrimeiroNumeroDoCPF()).isEqualTo(1);
	}
	
	@Test
	void deveRetornarArrayPreenchidoComDeterminadoNumero() {
		int numero = 7;
		
		String[] arrayRetornado = validador.preencherArrayComPrimeiroNumeroDoCPF(numero);
		String[] arrayEsperado = Integer.toString(numero).repeat(11).split("");
		
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
		validador.setCpf("12345678901");
		assertThat(validador.numerosDoCPFSaoIguais(validador.getCpf())).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeNumeroNaoForIgualAoCPF() {
		validador.setCpf("12345678901");
		String primeiroNumero = validador.getCpf().split("")[0];
		assertThat(validador.numerosDoCPFSaoIguais(primeiroNumero)).isFalse();
	}
	
	@Test
	void deveRetornarTrueSeEstruturaEstiverOK() {
		validador.setCpf("479.885.790-45");
		assertThat(validador.ehValida()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeCPFForNulo() {
		assertThat(validador.ehValida()).isFalse();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoPossuirOnzeNumeros() {
		validador.setCpf("1234");
		assertThat(validador.ehValida()).isFalse();
	}
	
	@Test
	void deveRetornarFalseSeCPFNaoPossuirTodosCaracteresValidos() {
		validador.setCpf("479.885.790*45");
		assertThat(validador.ehValida()).isFalse();
	}
}
