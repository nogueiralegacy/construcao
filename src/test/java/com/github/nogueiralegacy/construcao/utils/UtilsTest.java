package com.github.nogueiralegacy.construcao.utils;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void arquivoNaoEncontradoNoResources() {
        // O nome de um arquivo que não existe
        String fileName = "inexistente";

        Utils utils = new Utils();

        assertNull(utils.getPath(fileName));
    }

    @Test
    void arquivoEncontradoNoResources() {
        // O nome de um arquivo que existe
        String fileName = "config.properties";

        Utils utils = new Utils();

        assertNotNull(utils.getPath(fileName));
    }

    @Test
    void propriedadesCarregadasCorretamente() {
        String fileName = "config.properties";
        //Propriedades esperadas
        String urlBanco = "url_do_banco";
        String usarioBanco = "seu_usuario";
        String senhaBanco = "sua_senha";

        Path pathProperties = new Utils().getPath(fileName);

        Properties properties = Utils.getProperties(pathProperties);

        assertEquals(urlBanco, properties.getProperty("db.url"));
        assertEquals(usarioBanco, properties.getProperty("db.username"));
        assertEquals(senhaBanco, properties.getProperty("db.password"));
    }

    @Test
    void carregandoPropertiesComArquivoNaoEncontradoNoResources() {
        // Não existe arquivo no resources com esse nome
        String fileName = "inexistente";

        Path pathProperties = new Utils().getPath(fileName);

        Properties properties = Utils.getProperties(pathProperties);

        assertTrue(properties.isEmpty());
    }
}
