package com.luis.validador.core;

import com.luis.validador.exception.CPFInvalidoException;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

class ValidadorTest {

    Validador validador;

    @Test
    @DisplayName("Testa todos os casos em que há possibilidade de lançar exceção")
    public void todosOsCasosDeExcecao() {
        String cpfComNumerosIguais = "11111111111";
        String cpfComLetra = "40028922A18";
        String cpfComLengthDiferenteDeOnze = "123";
        
        assertThatThrownBy(() -> validador = new Validador(cpfComNumerosIguais)).isInstanceOf(CPFInvalidoException.class);
        assertThatThrownBy(() -> validador = new Validador(cpfComLetra)).isInstanceOf(CPFInvalidoException.class);
        assertThatThrownBy(() -> validador = new Validador(cpfComLengthDiferenteDeOnze)).isInstanceOf(CPFInvalidoException.class);
    }

    @Test
    @DisplayName("Testa todos os casos em que o CPF é válido ou inválido")
    public void todosOsCasosEmQueOCPFEhValidoOuInvalido() {
        String cpfValido = "529.982.247-25";
        String cpfInvalido = "123.424.651-40";
        
        assertThat(new Validador(cpfValido).validar()).isTrue();
        assertThat(new Validador(cpfInvalido).validar()).isFalse();
    }

    @Test
    @DisplayName("Testa todos os casos em que há possibilidade de lançar exceção")
    public void todosOsCasosEmQueOsDigitosSaoValidosOuInvalidos() {
        String cpfComPrimeiroDigitoVerificadorValido = "529.982.247-25";
        String cpfComPrimeiroDigitoVerificadorInvalido = "236.865.184-18";
        String cpfComSegundoDigitoVerificadorValido = "529.982.247-25";
        String cpfComSegundoDigitoVerificadorInvalido = "236.865.184-18";
        
        assertThat(new Validador(cpfComPrimeiroDigitoVerificadorValido).validarPrimeiroDigito()).isTrue();
        assertThat(new Validador(cpfComPrimeiroDigitoVerificadorInvalido).validarPrimeiroDigito()).isFalse();
        assertThat(new Validador(cpfComSegundoDigitoVerificadorValido).validarSegundoDigito()).isTrue();
        assertThat(new Validador(cpfComSegundoDigitoVerificadorInvalido).validarSegundoDigito()).isFalse();
    }

    @Test
    public void todosOsCasosEmQueHaMultiplicacaoDosNumerosDoCPF() {
        String cpf = "236.865.184-58";
        validador = new Validador(cpf);
        
        int resultado = validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(10, 2);
        assertThat(resultado).isEqualTo(248);
        
        resultado = validador.obterSomaDaMultiplicacaoDosNumerosDoCpf(11, 2);
        assertThat(resultado).isEqualTo(301);
    }
    
}
