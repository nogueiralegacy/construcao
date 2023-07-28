package com.github.nogueiralegacy.construcao.service;

import com.github.nogueiralegacy.construcao.banco.orm.Usuario;
import com.github.nogueiralegacy.construcao.utils.dto.ProjetoDTO;

import org.springframework.boot.test.context.SpringBootTest;


// TODO: implementar testes
@SpringBootTest
class ProjetoServiceTest {
    ProjetoDTO projetoDTO = new ProjetoDTO
            (
                    "Projeto Teste",
                    "Projeto para testes",
                    "2021-10-10 15:21:03",
                    "testador",
                    new String[]{"testador"}
            );
    Usuario usuario;



}
