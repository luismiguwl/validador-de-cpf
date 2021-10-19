package com.luis.validador.model;

public class CPF {
    
    private String cpf;

    private int primeiroDigitoVerificador;
    private int segundoDigitoVerificador;

    public CPF(String numeros) {
        this.cpf = numeros;
        definirDigitosVerificadores();
    }
    
    public String get() {
        return cpf;
    }

    public int getPrimeiroDigitoVerificador() {
        return primeiroDigitoVerificador;
    }

    public int getSegundoDigitoVerificador() {
        return segundoDigitoVerificador;
    }
    
    private void definirDigitosVerificadores() {
        primeiroDigitoVerificador = Integer.parseInt(cpf.split("")[cpf.length() - 2]);
        segundoDigitoVerificador = Integer.parseInt(cpf.split("")[cpf.length() - 1]);
    }
}
