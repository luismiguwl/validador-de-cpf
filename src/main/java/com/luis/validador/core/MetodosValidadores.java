package com.luis.validador.core;

import com.luis.validador.exception.CPFInvalidoException;

public interface MetodosValidadores {
    void verificarSeCpfNaoEhNulo(String cpf);
    void verificarSeExistemApenasCaracteresValidos(String cpf);
    void verificarSeCPFPossuiOnzeNumeros(String cpf);
    void verificarSeNumerosDoCPFNaoSaoIguais(String cpf);
}
