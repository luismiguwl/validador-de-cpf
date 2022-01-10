package com.luis.validador.core;

import static com.luis.validador.utils.Utils.extrairApenasNumerosDeUmaString;

import java.util.Arrays;

import com.luis.validador.exception.CPFInvalidoException;
import com.luis.validador.model.CPF;

public class Validador implements MetodosValidadores {

    private final int QUANTIDADE_DE_NUMEROS_DE_UM_CPF = 11;
    private CPF cpf;

    public Validador(String supostoCpf) {
        System.out.println("String recebida = " + supostoCpf);

        verificarSeCpfNaoEhNulo(supostoCpf);
        verificarSeExistemApenasCaracteresValidos(supostoCpf);
        verificarSeCPFPossuiOnzeNumeros(supostoCpf);
        verificarSeNumerosDoCPFNaoSaoIguais(supostoCpf);
        
        this.cpf = new CPF(supostoCpf);
    }

    @Override
    public void verificarSeCpfNaoEhNulo(String cpf) {
        if (cpf == null) {
            throw new CPFInvalidoException("CPF não pode ser nulo!");
        }
    }

    @Override
    public void verificarSeExistemApenasCaracteresValidos(String caracteres) {
        String caracteresAceitos = "0123456789-.";
        boolean possuiTodosCaracteresValidos
                = Arrays.stream(caracteres.split(""))
                        .allMatch(caracter -> caracteresAceitos.contains(caracter));

        if (!possuiTodosCaracteresValidos) {
            throw new CPFInvalidoException("Caracteres aceitos num CPF\n\t- 11 números\n\t- 2 pontos\n\t- 1 hífen\n\tExemplo: 123.456.789-00");
        }
    }

    @Override
    public void verificarSeCPFPossuiOnzeNumeros(String cpf) {
        boolean possuiOnzeNumeros = extrairApenasNumerosDeUmaString(cpf).length == QUANTIDADE_DE_NUMEROS_DE_UM_CPF;
        
        if (!possuiOnzeNumeros) {
            throw new CPFInvalidoException("CPF deve possuir 11 números!");
        }
    }
    
    @Override
    public void verificarSeNumerosDoCPFNaoSaoIguais(String supostoCpf) {
        String primeiroCaracterDoCpf = Character.toString(supostoCpf.charAt(0));
        int primeiroNumeroDoCpf = Integer.parseInt(primeiroCaracterDoCpf);

        String[] numeros = new String[QUANTIDADE_DE_NUMEROS_DE_UM_CPF];
        Arrays.fill(numeros, Integer.toString(primeiroNumeroDoCpf));

        String primeiroNumeroDoCpfRepetido = String.join("", numeros);

        boolean numerosSaoIguais = supostoCpf.equals(primeiroNumeroDoCpfRepetido);
        
        if (numerosSaoIguais) {
            throw new CPFInvalidoException("Números do CPF não podem ser todos iguais!");
        }
    }

    public boolean validar() {
        return validarPrimeiroDigito() && validarSegundoDigito();
    }

    public boolean validarPrimeiroDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(10, 2);
        return digitoEhValido(somaDaMultiplicacaoDosNumeros, cpf.getPrimeiroDigitoVerificador());
    }

    public boolean validarSegundoDigito() {
        int somaDaMultiplicacaoDosNumeros = obterSomaDaMultiplicacaoDosNumerosDoCpf(11, 2);
        return digitoEhValido(somaDaMultiplicacaoDosNumeros, cpf.getSegundoDigitoVerificador());
    }

    private boolean digitoEhValido(int multiplicacao, int digitoVerificador) {
        return (multiplicacao * 10) % 11 == digitoVerificador;
    }

    public int obterSomaDaMultiplicacaoDosNumerosDoCpf(int maximo, int minimo) {
        String[] numerosDoCPF = extrairApenasNumerosDeUmaString(cpf.get());
        int soma = 0, indice = 0;

        for (int i = maximo; i >= minimo; i--) {
            soma += Integer.parseInt(numerosDoCPF[indice]) * i;
            indice++;
        }

        return soma;
    }

}
