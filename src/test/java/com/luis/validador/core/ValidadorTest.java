package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidadorTest {
	Validador validador;
	String numerosDoCPF = getCPFValido();

	@BeforeEach
	void setUp() {
		validador = new Validador(numerosDoCPF);
	}
	
	private String getCPFValido() {
		return "479.885.790-45";
	}
	
	private String getCPFComEstruturaInvalida() {
		return "479.885.790";
	}
	
	@Test
	void deveRetornarTrueSeEstruturaForValida() {
		assertThat(validador.validar()).isTrue();
	}
	
	@Test
	void deveRetornarFalseSeEstruturaForInvalida() {
		numerosDoCPF = getCPFComEstruturaInvalida();
		setUp();
		assertThat(validador.validar()).isFalse();
	}
}
