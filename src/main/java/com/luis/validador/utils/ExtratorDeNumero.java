package com.luis.validador.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExtratorDeNumero {

    private String texto;

    public ExtratorDeNumero(String texto) {
        this.texto = texto;
    }

    public ExtratorDeNumero() {
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String[] extrairApenasNumerosDaString() {
        String[] caracteresDoCPF = texto.split("");

        return Arrays.stream(caracteresDoCPF)
                .filter(caracter -> caracter.matches("[0-9]"))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }
}
