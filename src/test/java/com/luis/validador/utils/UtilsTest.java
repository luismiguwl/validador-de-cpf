package com.luis.validador.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;

class UtilsTest {

    ExtratorDeNumero extrator = new ExtratorDeNumero();

    @Test
    void deveExtrairApenasUmNumeroDaString() {
        extrator.setTexto("Way 2 Sexy");
        assertThat(extrator.extrairApenasNumerosDaString()).hasSize(1);
        assertThat(extrator.extrairApenasNumerosDaString()).contains("2");
    }
    
    @Test
    void deveExtrairApenasUmNumeroDaStringSeStringNaoPossuirEspaco() {
        extrator.setTexto("Way2Sexy");
        assertThat(extrator.extrairApenasNumerosDaString()).hasSize(1);
        assertThat(extrator.extrairApenasNumerosDaString()).contains("2");
    }
    
    @Test
    void deveExtrairDoisNumerosDaStringSeStringNaoPossuirEspaco() {
        extrator.setTexto("Way22Sexy");
        assertThat(extrator.extrairApenasNumerosDaString()).hasSize(2);
        assertThat(extrator.extrairApenasNumerosDaString()).contains("2", "2");
    }

    @Test
    void deveRetornarArrayVazioSeStringNaoPossuirNumeros() {
        extrator.setTexto("On That Time");
        assertThat(extrator.extrairApenasNumerosDaString()).isEmpty();
    }
    
    @Test
    void deveRetornarArrayVazioSeStringForVazia() {
        extrator.setTexto("");
        assertThat(extrator.extrairApenasNumerosDaString()).hasSize(0);
    }
    
}
