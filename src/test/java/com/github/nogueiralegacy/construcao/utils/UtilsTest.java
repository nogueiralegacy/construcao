package com.github.nogueiralegacy.construcao.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void nomeDeArquivoInexistenteInvalido() {
        // O nome de um arquivo que não existe
        String fileName = "inexistente";

        Utils utils = new Utils();

        assertNull(utils.getPath(fileName));
    }

    @Test
    void nomeDeArquivoExistente() {
        // O nome de um arquivo que existe
        String fileName = "config.properties";

        Utils utils = new Utils();

        assertNotNull(utils.getPath(fileName));
    }
}
