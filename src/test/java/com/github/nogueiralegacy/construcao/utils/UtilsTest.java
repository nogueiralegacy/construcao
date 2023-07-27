package com.github.nogueiralegacy.construcao.utils;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDateTime;
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
        String fileName = "configTest.properties";

        Utils utils = new Utils();

        assertNotNull(utils.getPath(fileName));
    }

    @Test
    void propriedadesCarregadasCorretamente() {
        String fileName = "configTest.properties";
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

    @Test
    void convertendoDataValida() {
        LocalDateTime dataEsperada = LocalDateTime.of(2021, 10, 10, 10, 10, 10);

        String dataString = "2021-10-10 10:10:10";
        LocalDateTime dataConvertida = Utils.toLocalDateTime(dataString);

        assertEquals(dataEsperada, dataConvertida);
    }

    @Test
    void convertendoDataInvalida() {
        String dataString = "2021-10-10 10:10:10:10";

        LocalDateTime dataConvertida = Utils.toLocalDateTime(dataString);

        assertThrows(IllegalArgumentException.class, () -> Utils.toLocalDateTime(dataString));
    }
}
