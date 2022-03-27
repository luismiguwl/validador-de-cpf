package com.luis.validador.core;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.luis.validador.CPFParaTeste.*;

class ValidadorDeEstruturaTest {

    ValidadorDeEstrutura estrutura;
    String cpf = CPF_VALIDO.get();
    
    @BeforeEach
    void setUp() {
        estrutura = new ValidadorDeEstrutura(cpf);
    }
    
    @Test
    void deveRetornarTrueSeEstruturaDoCpfContendoPontuacaoForValida() {
        assertTrue(estrutura.ehValida());
    }
    
    @Test
    void deveRetornarTrueSeEstruturaDoCpfSemPontuacaoForValida() {
        String cpfSemPontuacao = cpf.replaceAll("[.-]", "");
        estrutura.setCpf(cpfSemPontuacao);
        assertTrue(estrutura.ehValida());
    }
    
    @Test
    void deveRetornarFalseSeCPFConterMaisQueOnzeNumeros() {
        estrutura.setCpf(cpf.concat("1"));
        assertFalse(estrutura.ehValida());
        
        estrutura.setCpf(cpf.concat("1").replaceAll("[.-]", ""));
        assertFalse(estrutura.ehValida());
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
        assertTrue(estrutura.todosCaracteresSaoIguais("aaaa"));
        assertTrue(estrutura.todosCaracteresSaoIguais("...."));
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
    
    @Test
    void deveRetornarFalseSeTextoPossuirLetra() {
        estrutura.setCpf("123.aaa.232-00");
        assertFalse(estrutura.ehValida());
    }
    
}
