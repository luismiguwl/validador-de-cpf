package com.luis.validador.core;

import static com.luis.validador.utils.Utils.extrairApenasNumerosDeUmaString;

import java.util.Arrays;

import com.luis.validador.exception.CPFInvalidoException;
import com.luis.validador.model.CPF;

public class Validador implements MetodosValidadores {

    private final int QUANTIDADE_DE_NUMEROS_DE_UM_CPF = 11;
    private CPF cpf;
    private String stringRecebida;

    public Validador(String stringRecebida) {
        this.stringRecebida = stringRecebida;
    }
    
    public Validador(CPF cpf) {
        this.cpf = cpf;
        this.stringRecebida = cpf.get();
    }

    public boolean validar() throws CPFInvalidoException {
        verificarSeCpfNaoEhNulo();
        verificarSeExistemApenasCaracteresValidos();
        verificarSeCPFPossuiOnzeNumeros();
        verificarSeNumerosDoCPFNaoSaoIguais();
        
        this.cpf = new CPF(stringRecebida);
        
        return validarPrimeiroDigito() && validarSegundoDigito();
    }
    
    @Override
    public void verificarSeCpfNaoEhNulo() throws CPFInvalidoException {
        if (stringRecebida == null) {
            throw new CPFInvalidoException("CPF não pode ser nulo!");
        }
    }

    @Override
    public void verificarSeExistemApenasCaracteresValidos() throws CPFInvalidoException {
        String caracteresAceitos = "0123456789-.";
        boolean possuiTodosCaracteresValidos
                = Arrays.stream(stringRecebida.split(""))
                        .allMatch(caracter -> caracteresAceitos.contains(caracter));

        if (!possuiTodosCaracteresValidos) {
            throw new CPFInvalidoException("Caracteres aceitos num CPF\n\t- 11 números\n\t- 2 pontos\n\t- 1 hífen\n\tExemplo: 123.456.789-00");
        }
    }

    @Override
    public void verificarSeCPFPossuiOnzeNumeros() throws CPFInvalidoException {
        boolean possuiOnzeNumeros = extrairApenasNumerosDeUmaString(stringRecebida).length == QUANTIDADE_DE_NUMEROS_DE_UM_CPF;
        
        if (!possuiOnzeNumeros) {
            throw new CPFInvalidoException("CPF deve possuir 11 números!");
        }
    }
    
    @Override
    public void verificarSeNumerosDoCPFNaoSaoIguais() throws CPFInvalidoException {
        String primeiroCaracterDoCpf = Character.toString(stringRecebida.charAt(0));
        int primeiroNumeroDoCpf = Integer.parseInt(primeiroCaracterDoCpf);

        String[] numeros = new String[QUANTIDADE_DE_NUMEROS_DE_UM_CPF];
        Arrays.fill(numeros, Integer.toString(primeiroNumeroDoCpf));

        String primeiroNumeroDoCpfRepetido = String.join("", numeros);

        boolean numerosSaoIguais = stringRecebida.equals(primeiroNumeroDoCpfRepetido);
        
        if (numerosSaoIguais) {
            throw new CPFInvalidoException("Números do CPF não podem ser todos iguais!");
        }
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
