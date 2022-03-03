package com.luis.validador.core;

import com.luis.validador.model.*;
import static com.luis.validador.model.DigitoVerificador.*;
import com.luis.validador.utils.*;

public class ValidadorDeDigitos {
    private CPF cpf;
    
    public ValidadorDeDigitos(CPF cpf) {
        this.cpf = cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    
    public boolean digitosVerificadoresSaoValidos() {
        return validarPrimeiroDigito() && validarSegundoDigito();
    }
    
    public boolean validarPrimeiroDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(PRIMEIRO);
        return ehValido(somaDaMultiplicacaoDosNumeros, cpf.getPrimeiroDigitoVerificador());
    }

    public boolean validarSegundoDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(SEGUNDO);
        return ehValido(somaDaMultiplicacaoDosNumeros, cpf.getSegundoDigitoVerificador());
    }

    public int obterSomaDaMultiplicacaoDosNumerosDoCpf(DigitoVerificador digito) {
        ExtratorDeNumero extrator = new ExtratorDeNumero(cpf.get());
    	String[] numerosDoCPF = extrator.extrairApenasNumerosDaString();
        int soma = 0, indice = 0;

        for (int i = digito.getQuantidadeDeMultiplicacoes(); i >= digito.getNumeroMinimo(); i--) {
            soma += Integer.parseInt(numerosDoCPF[indice]) * i;
            indice++;
        }

        return soma;
    }
}
