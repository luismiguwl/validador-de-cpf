package com.luis.validador.core;

import com.luis.validador.exception.CPFInvalidoException;
import com.luis.validador.model.CPF;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Validador {

    private CPF cpf;

    public Validador(String supostoCpf) {
        if (contemLetra(supostoCpf)) {
            throw new CPFInvalidoException("CPF não pode possuir letra(s)!");
        } else if (!possuiOnzeNumeros(supostoCpf)) {
            throw new CPFInvalidoException("CPF deve possuir 11 números!");
        } else if (supostoCpf.length() > 14) {
        	throw new CPFInvalidoException("Um CPF possui no máximo 14 caracteres!");
        } else {
        	this.cpf = new CPF(supostoCpf);
        }
    }

    private boolean contemLetra(String caracteres) {
        return Arrays.stream(caracteres.split(""))
                .anyMatch(caracter -> "abcdefghijklmnopqrstuvwxyz".contains(caracter));
    }

    private boolean possuiOnzeNumeros(String caracteres) {
        String[] numeros = Arrays.stream(caracteres.split(""))
                .filter(caracter -> "0123456789".contains(caracter))
                .collect(Collectors.toList())
                .toArray(new String[0]);

        return numeros.length == 11;
    }

    public boolean validar() {
        return !numerosDoCpfSaoIguais() && validarPrimeiroDigito() && validarSegundoDigito();
    }

    public boolean validarPrimeiroDigito() {
        int somaDaMultiplicacaoDosNumeros = multiplicarNumerosDoCpf(10, 2);
        return (somaDaMultiplicacaoDosNumeros * 10) % 11 == cpf.getDigitosVerificadores()[0];
    }

    public boolean validarSegundoDigito() {
        int somaDaMultiplicacaoDosNumeros = multiplicarNumerosDoCpf(11, 2);
        return (somaDaMultiplicacaoDosNumeros * 10) % 11 == cpf.getDigitosVerificadores()[1];
    }

    public int multiplicarNumerosDoCpf(int maximo, int minimo) {
        String[] numerosDoCPF = obterApenasNumerosDoCpf();
        int soma = 0;
        int indice = 0;

        for (int i = maximo; i >= minimo; i--) {
            soma += Integer.parseInt(numerosDoCPF[indice]) * i;
            indice++;
        }

        return soma;
    }

    public String[] obterApenasNumerosDoCpf() {
        String[] caracteresDoCPF = cpf.get().split("");

        return Arrays.stream(caracteresDoCPF)
                .filter(caracter -> "1234567890".contains(caracter))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    public boolean numerosDoCpfSaoIguais() {
        String primeiroCaracterDoCpf = Character.toString(cpf.get().charAt(0));
        int primeiroNumeroDoCpf = Integer.parseInt(primeiroCaracterDoCpf);

        String[] numeros = new String[11];
        Arrays.fill(numeros, Integer.toString(primeiroNumeroDoCpf));

        String numerosString = String.join("", numeros);

        return cpf.get().equals(numerosString);
    }

}
