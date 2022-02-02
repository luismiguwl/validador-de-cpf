package com.luis.validador.core;

import com.luis.validador.model.CPF;
import com.luis.validador.utils.ExtratorDeNumero;

public class ValidadorDeDigitos {
    private final CPF cpf;
    
    public ValidadorDeDigitos(CPF cpf) {
        this.cpf = cpf;
    }
    
    public boolean digitosVerificadoresSaoValidos() {
        return validarPrimeiroDigito() && validarSegundoDigito();
    }
    
    public boolean validarPrimeiroDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(10, 2);
        return digitoEhValido(somaDaMultiplicacaoDosNumeros, cpf.getPrimeiroDigitoVerificador());
    }

    public boolean validarSegundoDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(11, 2);
        return digitoEhValido(somaDaMultiplicacaoDosNumeros, cpf.getSegundoDigitoVerificador());
    }

    public boolean digitoEhValido(int multiplicacao, int digitoVerificador) {
        return (multiplicacao * 10) % 11 == digitoVerificador;
    }

    public int obterSomaDaMultiplicacaoDosNumerosDoCpf(int maximo, int minimo) {
        ExtratorDeNumero extrator = new ExtratorDeNumero(cpf.get());
    	String[] numerosDoCPF = extrator.extrairApenasNumerosDaString();
        int soma = 0, indice = 0;

        for (int i = maximo; i >= minimo; i--) {
            soma += Integer.parseInt(numerosDoCPF[indice]) * i;
            indice++;
        }

        return soma;
    }
}
