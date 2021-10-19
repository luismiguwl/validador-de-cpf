package com.luis.validador.core;

import static com.luis.validador.utils.Utils.extrairApenasNumerosDeUmaString;

import java.util.Arrays;

import com.luis.validador.exception.CPFInvalidoException;
import com.luis.validador.model.CPF;

public class Validador {

    private CPF cpf;

    public Validador(String supostoCpf) {
        if (contemLetra(supostoCpf)) {
            throw new CPFInvalidoException("CPF não pode possuir letra(s)!");
        } else if (!possuiOnzeNumeros(supostoCpf)) {
            throw new CPFInvalidoException("CPF deve possuir 11 números!");
        } else if (numerosDoCpfSaoIguais(supostoCpf)) {
        	throw new CPFInvalidoException("Números do CPF não podem ser todos iguais!");
        }
        
        this.cpf = new CPF(supostoCpf);
    }

    public boolean contemLetra(String caracteres) {
        return Arrays.stream(caracteres.split(""))
        		.map(caracter -> caracter.toLowerCase())
                .anyMatch(caracter -> "abcdefghijklmnopqrstuvwxyz".contains(caracter));
    }

    public boolean possuiOnzeNumeros(String caracteres) {
        return extrairApenasNumerosDeUmaString(caracteres).length == 11;
    }
    
    public boolean numerosDoCpfSaoIguais(String supostoCpf) {
        String primeiroCaracterDoCpf = Character.toString(supostoCpf.charAt(0));
        int primeiroNumeroDoCpf = Integer.parseInt(primeiroCaracterDoCpf);

        String[] numeros = new String[11];
        Arrays.fill(numeros, Integer.toString(primeiroNumeroDoCpf));

        String primeiroNumeroDoCpfRepetido = String.join("", numeros);

        return supostoCpf.equals(primeiroNumeroDoCpfRepetido);
    }

    public boolean validar() {
        return validarPrimeiroDigito() && validarSegundoDigito();
    }

    public boolean validarPrimeiroDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(10, 2);
        return (somaDaMultiplicacaoDosNumeros * 10) % 11 == cpf.getPrimeiroDigitoVerificador();
    }

    public boolean validarSegundoDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(11, 2);
        return (somaDaMultiplicacaoDosNumeros * 10) % 11 == cpf.getSegundoDigitoVerificador();
    }

    public int obterSomaDaMultiplicacaoDosNumerosDoCpf(int maximo, int minimo) {
        String[] numerosDoCPF = extrairApenasNumerosDeUmaString(cpf.get());
        int soma = 0, indice = 0;

        for (int i = maximo; i >= minimo; i--) {
            soma += Integer.parseInt(numerosDoCPF[indice]) * i;
            indice++;
        }

        return soma;
    }

}
