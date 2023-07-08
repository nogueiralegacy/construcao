package com.github.nogueiralegacy.construcao.banco;

import com.github.nogueiralegacy.construcao.utils.Utils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ConectorTest {

    @Test
    void conexaoBancoValida() {
        Utils utils = new Utils();

        // Arquivo com as propriedades de conexão com o banco de dados
        String fileName = "config.properties";

        Path pathProperties = utils.getPath(fileName);

        Properties properties = Utils.getProperties(pathProperties);
        String url = properties.getProperty("db.url");
        String usuario = properties.getProperty("db.username");
        String senha = properties.getProperty("db.password");

        Conector conector = new Conector(url, usuario, senha);

        try (Connection conexao = conector.conectar()) {
            assertNotNull(conexao);
        } catch (SQLException e) {
            fail("Conexão com o banco falhou: " + e.getMessage());
        }
    }
}
