package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;
import static com.luis.validador.CPFParaTeste.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidadorTest {

    Validador validador;
    String numerosDoCPF;

    @BeforeEach
    void setUp() {
        validador = new Validador(CPF_VALIDO.get());
    }

    @Test
    void deveRetornarTrueSeEstruturaForValida() {
        assertThat(validador.validar()).isTrue();
    }

    @Test
    void deveRetornarFalseSeEstruturaForInvalida() {
        validador.setSupostoCpf(CPF_INVALIDO.get());
        assertThat(validador.validar()).isFalse();
    }
}
