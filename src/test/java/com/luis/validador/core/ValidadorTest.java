package com.luis.validador.core;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.luis.validador.exception.CPFInvalidoException;

class ValidadorTest {

    Validador validador;

    @Test
    @DisplayName("Deve retornar true se todos os números do CPF forem iguais")
    public void cpfComNumerosIguaisTest() {
        String cpf = "11111111111";
        assertThatThrownBy(() -> validador = new Validador(cpf)).isInstanceOf(CPFInvalidoException.class);
    }

    @Test
    @DisplayName("Deve retornar exceção se CPF conter letra")
    public void cpfPossuiLetraTest() {
        String cpf = "12EA3567842";
        assertThatThrownBy(() -> validador = new Validador(cpf)).isInstanceOf(CPFInvalidoException.class);
    }

    @Test
    @DisplayName("Deve retornar exceção se CPF possuir length diferente de 11")
    public void cpfComLengthDiferenteDeOnzeTest() {
        String cpf = "112342";
        assertThatThrownBy(() -> validador = new Validador(cpf)).isInstanceOf(CPFInvalidoException.class);
    }

    @Test
    @DisplayName("Deve retornar true se CPF for válido")
    public void cpfValidoTest() {
        String cpf = "529.982.247-25";
        validador = new Validador(cpf);

        assertThat(validador.validar()).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false se CPF for inválido")
    public void cpfInvalidoTest() {
        String cpf = "123.424.651-40";
        validador = new Validador(cpf);

        assertThat(validador.validar()).isFalse();
    }

    @Test
    @DisplayName("Deve retornar true se primeiro dígito for válido")
    public void validarPrimeiroDigitoValidoTest() {
        String cpf = "529.982.247-25";
        validador = new Validador(cpf);

        assertThat(validador.validarPrimeiroDigito()).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false se primeiro dígito for inválido")
    public void validarPrimeiroDigitoInvalidoTest() {
        String cpf = "236.865.184-18";
        validador = new Validador(cpf);

        assertThat(validador.validarPrimeiroDigito()).isFalse();
    }

    @Test
    @DisplayName("Deve retornar true se segundo dígito for válido")
    public void validarSegundoDigitoValidoTest() {
        String cpf = "529.982.247-25";
        validador = new Validador(cpf);

        assertThat(validador.validarSegundoDigito()).isTrue();
        assertThat(new Validador(cpf).validar()).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false se segundo dígito for inválido")
    public void validarSegundoDigitoInvalidoTest() {
        String cpf = "236.865.184-18";
        validador = new Validador(cpf);

        assertThat(validador.validarSegundoDigito()).isFalse();
        assertThat(new Validador(cpf).validar()).isFalse();
    }

    @Test
    @DisplayName("Deve retornar o resultado da multiplicação dos números do CPF")
    public void multiplicarNumeroDoCpfTest() {
        String cpf = "236.865.184-58";
        validador = new Validador(cpf);

        int resultado = validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(10, 2);
        assertThat(resultado).isEqualTo(248);
    }

    @Test
    @DisplayName("Deve retornar o resultado da multiplicação dos números do CPF #2")
    public void multiplicarNumeroDoCpf2est() {
        String cpf = "236.865.184-58";
        validador = new Validador(cpf);

        int resultado = validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(11, 2);
        assertThat(resultado).isEqualTo(301);
    }

}
