package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.luis.validador.exception.CPFInvalidoException;

class ValidadorTest {
	Validador validador;
	
	@Test
	@DisplayName("Deve retornar true se todos os números do CPF forem iguais")
	public void cpfComNumerosIguaisTest() {
		String cpf = "11111111111";
		assertThrows(CPFInvalidoException.class, () -> validador = new Validador(cpf), "Números do CPF não podem ser todos iguais!");
	}
	
	@Test
	@DisplayName("Deve retornar exceção se CPF conter letra")
	public void cpfPossuiLetraTest() {
		String cpf = "12EA3567842";
		assertThrows(CPFInvalidoException.class, () -> validador = new Validador(cpf));
	}
	
	@Test
	@DisplayName("Deve retornar exceção se CPF possuir length diferente de 11")
	public void cpfComLengthDiferenteDeOnzeTest() {
		String cpf = "112342";
		assertThrows(CPFInvalidoException.class, () -> validador = new Validador(cpf));
	}
	
	@Test
	@DisplayName("Deve retornar true se CPF for válido")
	public void cpfValidoTest() {
		String cpf = "529.982.247-25";
		validador = new Validador(cpf);
		
		assertThat(validador.validar()).isTrue();
	}
	
	@Test
	@DisplayName("Deve retornar false se CPF for inválido")
	public void cpfInvalidoTest() {
		String cpf = "123.424.651-40";
		validador = new Validador(cpf);
		
		assertThat(validador.validar()).isFalse();
	}
	
	@Test
	@DisplayName("Deve retornar true se primeiro dígito for válido")
	public void validarPrimeiroDigitoValidoTest() {
		String cpf = "529.982.247-25";
		validador = new Validador(cpf);
		
		assertThat(validador.validarPrimeiroDigito()).isTrue();
	}
	
	@Test
	@DisplayName("Deve retornar false se primeiro dígito for inválido")
	public void validarPrimeiroDigitoInvalidoTest() {
		String cpf = "236.865.184-18";
		validador = new Validador(cpf);
		
		assertThat(validador.validarPrimeiroDigito()).isFalse();
	}
	
	@Test
	@DisplayName("Deve retornar true se segundo dígito for válido")
	public void validarSegundoDigitoValidoTest() {
		String cpf = "529.982.247-25";
		validador = new Validador(cpf);
		
		assertThat(validador.validarSegundoDigito()).isTrue();
		assertThat(new Validador(cpf).validar()).isTrue();
	}
	
	@Test
	@DisplayName("Deve retornar false se segundo dígito for inválido")
	public void validarSegundoDigitoInvalidoTest() {
		String cpf = "236.865.184-18";
		validador = new Validador(cpf);
		
		assertThat(validador.validarSegundoDigito()).isFalse();
		assertThat(new Validador(cpf).validar()).isFalse();
	}
}
