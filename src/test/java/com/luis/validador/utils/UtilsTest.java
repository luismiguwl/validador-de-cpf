package com.luis.validador.utils;

import static com.luis.validador.utils.ExtratorDeNumero.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {
	ExtratorDeNumero extrator;
	String texto;
	
	@BeforeEach
	void setUp() {
		extrator = new ExtratorDeNumero(texto);
	}
	
    @Test
    public void extrairApenasNumerosDeUmaStringTest() {
        extrator.setTexto("Way 2 Sexy");
        String[] resultadoEsperado = {"2"};
        
        assertThat(extrator.extrairApenasNumerosDaString()).isEqualTo(resultadoEsperado);
    }

    @Test
    public void extrairApenasNumerosDeUmaStringSemNumerosTest() {
        extrator.setTexto("On That Time");
        
        String[] valorEsperado = {};
        String[] valorRetornado = extrator.extrairApenasNumerosDaString();
        
        assertThat(valorRetornado).isEqualTo(valorEsperado);
    }
}
