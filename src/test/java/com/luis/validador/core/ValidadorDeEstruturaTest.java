package com.luis.validador.core;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidadorDeEstruturaTest {

    ValidadorDeEstrutura estrutura;
    String cpf = "123.106.770-51";
    
    @BeforeAll
    void setUp() {
        estrutura = new ValidadorDeEstrutura(cpf);
    }
    
    @Test
    void deveRetornarTrueSeEstruturaDoCpfContendoPontuacaoForValida() {
        assertTrue(estrutura.ehValida());
    }
    
    @Test
    void deveRetornarFalseSeEstruturaDoCpfSemPontuacaoForValida() {
        String cpfSemPontuacao = estrutura.getCpf().replaceAll("[.-]", "");
        estrutura.setCpf(cpfSemPontuacao);
        assertTrue(estrutura.ehValida());
    }
    
    @Test
    void deveRetornarFalseSeEstruturaPossuirNumerosIguais() {
        estrutura.setCpf("1".repeat(11));
        assertFalse(estrutura.ehValida());
    }
    
    @Test
    void deveRetornarTrueSeTodosCaracteresForemIguais() {
        assertTrue(estrutura.todosCaracteresSaoIguais("111"));
        assertTrue(estrutura.todosCaracteresSaoIguais(" 111 "));
        assertTrue(estrutura.todosCaracteresSaoIguais("1"));
    }
    
    @Test
    void deveRetornarFalseSeTodosCaracteresNaoForemIguais() {
        assertFalse(estrutura.todosCaracteresSaoIguais("112"));
        assertFalse(estrutura.todosCaracteresSaoIguais("111 1"));
    }
    
    @Test
    void deveRetornarFalseSeTextoEstiverVazio() {
        assertFalse(estrutura.todosCaracteresSaoIguais(""));
        assertFalse(estrutura.todosCaracteresSaoIguais("   "));
    }
    
    @Test
    void deveRetornarFalseSeTextoForNulo() {
        assertFalse(estrutura.todosCaracteresSaoIguais(null));
    }
}
