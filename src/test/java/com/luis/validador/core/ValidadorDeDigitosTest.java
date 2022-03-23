package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;
import static com.luis.validador.model.DigitoVerificador.*;

import org.junit.jupiter.api.*;

import com.luis.validador.model.*;
import static com.luis.validador.CPFParaTeste.*;

class ValidadorDeDigitosTest {

    ValidadorDeDigitos validador;
    CPF cpf;

    @BeforeEach
    void setUp() {
        cpf = new CPF(CPF_VALIDO);
        validador = new ValidadorDeDigitos(cpf);
    }

    @Test
    void deveRetornarTrueSePrimeiroDigitoForValido() {
        assertThat(validador.validarPrimeiroDigito()).isTrue();
    }

    @Test
    void deveRetornarTrueSeSegundoDigitoForValido() {
        assertThat(validador.validarSegundoDigito()).isTrue();
    }

    @Test
    void deveRetornarTrueSeDigitosEstiveremCorretos() {
        assertThat(validador.digitosVerificadoresSaoValidos()).isTrue();
    }

    @Test
    void deveRetornarFalseSeAlgumDosDigitosVerificadoresEstiveremCorretos() {
        validador.setCpf(new CPF(CPF_COM_APENAS_SEGUNDO_DIGITO_VALIDO));
        assertThat(validador.digitosVerificadoresSaoValidos()).isFalse();

        validador.setCpf(new CPF(CPF_COM_APENAS_PRIMEIRO_DIGITO_VALIDO));
        assertThat(validador.digitosVerificadoresSaoValidos()).isFalse();
    }

    @Test
    void deveRetornarFalseSePrimeiroDigitoForInvalido() {
        validador.setCpf(new CPF(CPF_COM_APENAS_SEGUNDO_DIGITO_VALIDO));
        assertThat(validador.validarPrimeiroDigito()).isFalse();
    }

    @Test
    void deveRetornarFalseSeSegundoDigitoForInvalido() {
        validador.setCpf(new CPF(CPF_COM_APENAS_PRIMEIRO_DIGITO_VALIDO));
        assertThat(validador.validarSegundoDigito()).isFalse();
    }

    @Test
    void deveRetornar359AoSomarAMultiplicacaoDosNumerosParaConferirPrimeiroDigito() {
        assertThat(validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(PRIMEIRO)).isEqualTo(359);
    }

    @Test
    void deveRetornar424AoSomarAMultiplicacaoDosNumerosParaConferirSegundoDigito() {
        assertThat(validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(SEGUNDO)).isEqualTo(424);
    }

    @Test
    void deveRetornarFalseAoTentarValidarDigitosVerificadoresQuandoSegundoDigitoForInvalido() {
        validador.setCpf(new CPF(CPF_COM_APENAS_PRIMEIRO_DIGITO_VALIDO));
        assertThat(validador.digitosVerificadoresSaoValidos()).isFalse();
    }

    @Test
    void deveRetornarFalseAoTentarValidarDigitosVerificadoresQuandoPrimeiroDigitoForInvalido() {
        validador.setCpf(new CPF(CPF_COM_APENAS_SEGUNDO_DIGITO_VALIDO));
        assertThat(validador.digitosVerificadoresSaoValidos()).isFalse();
    }
}
