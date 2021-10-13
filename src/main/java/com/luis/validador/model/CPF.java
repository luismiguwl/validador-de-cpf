package com.luis.validador.model;

public class CPF {
    
    private String cpf;
    private int[] digitosVerificadores;

    public CPF(String numeros) {
        this.cpf = numeros;
        definirDigitosVerificadores();
    }
    
    public String get() {
        return cpf;
    }
    
    public int[] getDigitosVerificadores() {
        return digitosVerificadores;
    }
    
    private void definirDigitosVerificadores() {
        int primeiroDigitoVerificador = Integer.parseInt(cpf.split("")[cpf.length() - 2]);
        int segundoDigitoVerificador = Integer.parseInt(cpf.split("")[cpf.length() - 1]);
        
        digitosVerificadores[0] = primeiroDigitoVerificador;
        digitosVerificadores[1] = segundoDigitoVerificador;
    }
}
