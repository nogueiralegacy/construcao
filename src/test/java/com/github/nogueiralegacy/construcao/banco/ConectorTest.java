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
        // Arquivo com as propriedades de conexão com o banco de dados
        String fileName = "config.properties";

        Path pathProperties = new Utils().getPath(fileName);

        Properties properties = Utils.getProperties(pathProperties);
        String url = properties.getProperty("db.url");
        String usuario = properties.getProperty("db.username");
        String senha = properties.getProperty("db.password");

        Conector conector = new Conector(url, usuario, senha);

        try (Connection conexao = conector.conectar()) {
            assertNotNull(conexao);
        }
         catch (SQLException sqlException) {
            fail("Conexao com o banco falhou: " + sqlException.getMessage());
        }
    }

    @Test
    void conexaoBancoInvalida() {
        // Arquivo com as propriedades de conexão com o banco de dados
        String fileName = "config.properties";

        Path pathProperties = new Utils().getPath(fileName);
        Properties properties = Utils.getProperties(pathProperties);

        String url = properties.getProperty("db.url");
        String usuario = properties.getProperty("db.username");
        String senha = "senha_invalida";

        Conector conector = new Conector(url, usuario, senha);

        assertThrows(SQLException.class, () -> conector.conectar(),
                "Conexao com o banco falhou");
    }
}
