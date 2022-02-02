package com.luis.validador.utils;

import static com.luis.validador.utils.ExtratorDeNumero.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilsTest {
	ExtratorDeNumero extrator;
	String texto;
	
	@BeforeEach
	void setUp() {
		extrator = new ExtratorDeNumero(texto);
	}
	
    @Test
    public void extrairApenasNumerosDeUmaStringTest() {
        texto = "Way 2 Sexy";
        setUp();
        
        String[] resultadoEsperado = {"2"};
        
        assertThat(extrator.extrairApenasNumerosDaString()).isEqualTo(resultadoEsperado);
    }

    @Test
    public void extrairApenasNumerosDeUmaStringSemNumerosTest() {
        texto = "On That Time";
        setUp();
        
        String[] valorEsperado = {};
        String[] valorRetornado = extrator.extrairApenasNumerosDaString();
        
        assertThat(valorRetornado).isEqualTo(valorEsperado);
    }
}
