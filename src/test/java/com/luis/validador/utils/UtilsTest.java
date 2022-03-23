package com.luis.validador.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;

class UtilsTest {

    ExtratorDeNumero extrator = new ExtratorDeNumero();

    @Test
    void extrairApenasNumerosDeUmaStringTest() {
        extrator.setTexto("Way 2 Sexy");
        String[] resultadoEsperado = {"2"};

        assertThat(extrator.extrairApenasNumerosDaString()).isEqualTo(resultadoEsperado);
    }

    @Test
    void extrairApenasNumerosDeUmaStringSemNumerosTest() {
        extrator.setTexto("On That Time");

        String[] valorEsperado = {};
        String[] valorRetornado = extrator.extrairApenasNumerosDaString();

        assertThat(valorRetornado).isEqualTo(valorEsperado);
    }
}
