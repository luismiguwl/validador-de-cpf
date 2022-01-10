package com.luis.validador.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {

    public static String[] extrairApenasNumerosDeUmaString(String linha) {
        String[] caracteresDoCPF = linha.split("");

        return Arrays.stream(caracteresDoCPF)
                .filter(caracter -> "1234567890".contains(caracter))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }
}
