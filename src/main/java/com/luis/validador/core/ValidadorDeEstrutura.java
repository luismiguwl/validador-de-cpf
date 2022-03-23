package com.luis.validador.core;

import java.util.List;

public class ValidadorDeEstrutura {
    private String cpf;
    private final List<String> FORMATOS_ACEITOS = List.of(
                "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}",
                "[0-9]{11}");

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
        boolean formatoOk = FORMATOS_ACEITOS.stream().anyMatch(padrao -> cpf.matches(padrao));
        return formatoOk && !todosCaracteresSaoIguais(cpf);
    }
    
    public boolean todosCaracteresSaoIguais(String texto) {
        if (texto == null || texto.trim().isBlank()) {
            return false;
        }
        
        texto = texto.trim();
        
        String primeiroCaracter = Character.toString(texto.charAt(0));
        String regex = String.format("[%s]{%d}", primeiroCaracter, texto.length());
        return texto.matches(regex);
    }
}
