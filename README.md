# Algoritmo
O algoritmo para validar um CPF é relativamente simples e possui poucas etapas que serão explicadas no decorrer deste README.

Vamos utilizar um CPF inválido para começar:
`
236.865.184-58
`

## 1ª etapa: comparar o primeiro dígito verificador
A primeira etapa para validar um CPF consiste em multiplicar seus números e comparar com o primeiro dígito verificador. Os dígitos verificadores de um CPF são seus últimos números. No nosso caso, `5` e `8`.

Multiplicamos os números do CPF, começando de 10 e indo até 2 e somando os resultados. Veja o exemplo abaixo:
`
2 * 10 + 3 * 9 + 6 * 8 + 8 * 7 + 6 * 6 + 5 * 5 + 1 * 4 + 8 * 3 + 4 * 2
`

Temos que o resultado da multiplicação é `248`.

Com o resultado da soma em mãos, vamos multiplicá-la por 10 e obter o resto da divisão por 11:

`
(248 * 10) % 11 => 5
`

Temos que o resto da divisão por 11 é `5`.

Agora, vamos comparar 5 com o primeiro dígito verificador:

`
5 == 5 -> true
`

Opa, números equivalentes! Agora, vamos verificar o segundo dígito.

## 2ª etapa: comparar segundo dígito verificador
Semelhente à primeira etapa, novamente vamos multiplicar os números do CPF, porém agora indo de 11 até 2, multiplicar a soma por 10 e obter o resto da divisão por 11:
`
2 * 11 + 3 * 10 + 6 * 9 + 8 * 8 + 6 * 7 + 5 * 6 + 1 * 5 + 8 * 4 + 4 * 3 + 5 * 2
`

Note que agora incluímos o primeiro dígito verificador à multiplicação.

O resultado da soma é `301`.

`
(301 * 10) % 11 => 7
`

Comparando os dígitos:
`
8 == 7 -> false
`

Como não conseguimos validar os dois dígitos verificadores, podemos concluir que o CPF `236.865.184-58` é inválido.

### Obs: um CPF só é válido quando os dígitos verificadores são equivalentes aos números gerados pelo resto da divisão por 11, tal como no 1ª etapa.



# Exemplo com CPF válido

Para exemplificar com um CPF válido, vamos usar um CPF gerado pelo código :)
`
671.831.858-26
`

Serei mais breve nesse exemplo porque já temos o algoritmo completo acima

Resultados da multiplicação:
```
1ª multiplicação: 273
2ª multiplicação: 324
```

Resto das divisões:
```
(273 * 10) % 11 => 2
(324 * 10) % 11 => 6
```

Dígitos verificadores:
```
2 e 6
```

Comparações:
```
2 == 2 && 6 == 6
```

O CPF `671.831.858-26` é válido.
