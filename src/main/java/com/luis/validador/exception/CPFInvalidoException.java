package com.luis.validador.exception;

public class CPFInvalidoException extends RuntimeException {

    public CPFInvalidoException(String mensagem) {
        super(mensagem);
        System.out.println(mensagem);
    }
}
