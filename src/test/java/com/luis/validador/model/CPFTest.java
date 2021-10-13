package com.luis.validador.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CPFTest {

	@Test
	public void digitosVerificadoresOkTest() {
		CPF cpf = new CPF("529.982.247-25");
		int[] digitosVerificadores = { 2, 5 };
		assertThat(cpf.getDigitosVerificadores()).isEqualTo(digitosVerificadores);
	}
}
