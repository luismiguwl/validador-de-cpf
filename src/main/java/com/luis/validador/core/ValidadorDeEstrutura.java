package com.luis.validador.core;

public class ValidadorDeEstrutura {
    private String cpf;

    public ValidadorDeEstrutura(String cpf) {
        this.cpf = cpf;
    }

    public ValidadorDeEstrutura() {
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean ehValida() {
        String apenasNumerosDoCPF = cpf.replaceAll("[^0-9]", "");
        boolean possuiOnzeNumeros = apenasNumerosDoCPF.length() == 11;
        return possuiOnzeNumeros && !todosCaracteresSaoIguais(cpf);
    }
    
    public boolean todosCaracteresSaoIguais(String texto) {
        if (texto == null || texto.trim().isBlank()) {
            return false;
        }
        
        texto = texto.trim();
        
        String primeiroCaracter = Character.toString(texto.charAt(0));
        return texto.equals(primeiroCaracter.repeat(texto.length()));
    }
}
