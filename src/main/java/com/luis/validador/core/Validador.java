package com.luis.validador.core;

import com.luis.validador.model.CPF;

public class Validador {
    
    private ValidadorDeEstrutura estrutura;
    private ValidadorDeDigitos validadorDeDigitos;
    private String supostoCpf;

    public Validador(String supostoCpf) {
        this.supostoCpf = supostoCpf;
        estrutura = new ValidadorDeEstrutura(supostoCpf);
    }

    public void setSupostoCpf(String supostoCpf) {
        this.supostoCpf = supostoCpf;
    }

    public boolean validar() {
        if (estrutura.ehValida()) {
            CPF cpf = new CPF(supostoCpf);
            validadorDeDigitos = new ValidadorDeDigitos(cpf);

            return validadorDeDigitos.digitosVerificadoresSaoValidos();
        }

        return false;
    }
    
}
