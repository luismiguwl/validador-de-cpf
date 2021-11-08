package com.luis.validador.utils;

import static com.luis.validador.utils.Utils.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void extrairApenasNumerosDeUmaStringTest() {
        String valorQualquer = "Way 2 Sexy";
        String[] resultadoEsperado = {"2"};
        
        assertThat(extrairApenasNumerosDeUmaString(valorQualquer)).isEqualTo(resultadoEsperado);
    }

    @Test
    public void extrairApenasNumerosDeUmaStringSemNumerosTest() {
        String valorQualquer = "leia o readme :)";
        String[] resultadoEsperado = {};
        
        assertThat(extrairApenasNumerosDeUmaString(valorQualquer)).isEqualTo(resultadoEsperado);
    }
}
