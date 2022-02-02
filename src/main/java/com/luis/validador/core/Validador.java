package com.luis.validador.core;

import com.luis.validador.model.CPF;

public class Validador {
    
    private ValidadorDeEstrutura estrutura;
    private ValidadorDeDigitos validadorDeDigitos;
    private final String stringRecebida;

    public Validador(String stringRecebida) {
        this.stringRecebida = stringRecebida;
        estrutura = new ValidadorDeEstrutura(stringRecebida);
    }

    public boolean validar() {
        if (!estrutura.ehValida()) {
            return false;
        }

        CPF cpf = new CPF(stringRecebida);
        validadorDeDigitos = new ValidadorDeDigitos(cpf);

        return validadorDeDigitos.digitosVerificadoresSaoValidos();
    }
    
}
