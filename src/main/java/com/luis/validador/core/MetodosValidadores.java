package com.luis.validador.core;

import com.luis.validador.exception.CPFInvalidoException;

public interface MetodosValidadores {
    void verificarSeCpfNaoEhNulo() throws CPFInvalidoException;
    void verificarSeExistemApenasCaracteresValidos() throws CPFInvalidoException;
    void verificarSeCPFPossuiOnzeNumeros() throws CPFInvalidoException;
    void verificarSeNumerosDoCPFNaoSaoIguais() throws CPFInvalidoException;
}
