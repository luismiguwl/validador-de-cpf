package com.luis.validador.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ExtratorDeNumero {
	private String texto;
	
	public ExtratorDeNumero(String texto) {
		this.texto = texto;
	}
	
    public String[] extrairApenasNumerosDaString() {
        String[] caracteresDoCPF = texto.split("");

        return Arrays.stream(caracteresDoCPF)
                .filter(caracter -> "1234567890".contains(caracter))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }
}
