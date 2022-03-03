package com.luis.validador.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.luis.validador.model.DigitoVerificador.*;
import static com.luis.validador.CPFParaTeste.*;

class DigitoVerificadorTest {

    CPF cpf = new CPF(CPF_VALIDO);

    @Test
    void deveRetornarTrueQuandoPrimeiroDigitoForValido() {
        assertTrue(ehValido(359, cpf.getPrimeiroDigitoVerificador()));
    }

    @Test
    void deveRetornarFalseQuandoPrimeiroDigitoForInvalido() {
        cpf = new CPF(CPF_COM_APENAS_SEGUNDO_DIGITO_VALIDO);
        assertFalse(ehValido(359, cpf.getPrimeiroDigitoVerificador()));
    }
    
    @Test
    void deveRetornarTrueQuandoSegundoDigitoForValido() {
        assertTrue(ehValido(424, cpf.getSegundoDigitoVerificador()));
    }
    
    @Test
    void deveRetornarFalseQuandoSegundoDigitoForInvalido() {
        cpf = new CPF(CPF_COM_APENAS_PRIMEIRO_DIGITO_VALIDO);
        assertFalse(ehValido(359, cpf.getSegundoDigitoVerificador()));
    }
}
