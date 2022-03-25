package com.luis.validador;

public enum CPFParaTeste {

    CPF_VALIDO("479.885.790-45"),
    CPF_INVALIDO("479.885.790"),
    CPF_COM_APENAS_PRIMEIRO_DIGITO_VALIDO("479.885.790-46"),
    CPF_COM_APENAS_SEGUNDO_DIGITO_VALIDO("479.885.790-35");

    private String cpf;

    private CPFParaTeste(String cpf) {
        this.cpf = cpf;
    }

    public String get() {
        return cpf;
    }

}
