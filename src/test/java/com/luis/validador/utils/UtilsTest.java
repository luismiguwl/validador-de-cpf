package com.luis.validador.utils;

import static com.luis.validador.utils.Utils.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilsTest {
	@Test
	public void extrairApenasNumerosDeUmaStringTest() {
		String[] resultadoEsperado = "1234".split("");
		String valorQualquer = "1paodequeijo2vaquinha3cafezinho4donda";
		assertThat(extrairApenasNumerosDeUmaString(valorQualquer)).isEqualTo(resultadoEsperado);
	}
	
	@Test
	public void extrairApenasNumerosDeUmaStringSemNumerosTest() {
		String[] resultadoEsperado = new String[0];
		String valorQualquer = "leia o readme :)";
		assertThat(extrairApenasNumerosDeUmaString(valorQualquer)).isEqualTo(resultadoEsperado);
	}
}
