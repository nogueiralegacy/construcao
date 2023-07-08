package com.github.nogueiralegacy.construcao.banco;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConectorTest {

    @Test
    void conexaoBancoValida() {
        Conector conector = new Conector(
                "postgresql://backend_api:api12345@144.22.237.177:5433/cs_note_sync",
                "root",
                "root123");

        try (Connection conexao = conector.conectar()) {
            assertNotNull(conexao);
        } catch (SQLException e) {
            fail("Conexão com o banco falhou: " + e.getMessage());
        }
    }
}
