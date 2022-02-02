package com.luis.validador.core;

import java.util.Arrays;

import com.luis.validador.utils.*;

public class ValidadorDeEstrutura {
    private final int QUANTIDADE_DE_NUMEROS_DE_UM_CPF = 11;
    private String cpf;

    public ValidadorDeEstrutura(String cpf) {
        this.cpf = cpf;
    }

    public boolean ehValida() {
        return !ehNulo() && possuiOnzeNumeros() && caracteresSaoValidos();
    }

    public boolean ehNulo() {
        return cpf == null;
    }

    public boolean possuiOnzeNumeros() {
    	ExtratorDeNumero extrator = new ExtratorDeNumero(cpf);
        String[] apenasNumeros = extrator.extrairApenasNumerosDaString();
        return apenasNumeros.length == QUANTIDADE_DE_NUMEROS_DE_UM_CPF;
    }

    public boolean caracteresSaoValidos() {
        String caracteresAceitos = "0123456789-.";
        boolean possuiTodosCaracteresValidos
                = Arrays.stream(cpf.split(""))
                        .allMatch(caracter -> caracteresAceitos.contains(caracter));

        return possuiTodosCaracteresValidos;
    }
    
    public boolean numerosSaoIguais() {
        int primeiroNumeroDoCPF = obterPrimeiroNumeroDoCPF();
        String[] numerosRepetidos = preencherArrayComPrimeiroNumeroDoCPF(primeiroNumeroDoCPF);
        String numerosUnidos = String.join("", numerosRepetidos);
        return numerosDoCPFSaoIguais(numerosUnidos);
    }
        
    public int obterPrimeiroNumeroDoCPF() {
        String primeiroCaracter = Character.toString(cpf.charAt(0));
        return Integer.parseInt(primeiroCaracter);
    }

    public String[] preencherArrayComPrimeiroNumeroDoCPF(int primeiroNumeroDoCPF) {
        if (primeiroNumeroDoCPF < 0) {
			throw new IllegalArgumentException("Número precisa ser positivo");
		}
    	
    	String[] numeros = new String[QUANTIDADE_DE_NUMEROS_DE_UM_CPF];
        Arrays.fill(numeros, Integer.toString(primeiroNumeroDoCPF));
        return numeros;
    }
    
    public boolean numerosDoCPFSaoIguais(String comparador) {
        return cpf.equals(comparador);
    }

}
