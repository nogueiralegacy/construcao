package com.github.nogueiralegacy.construcao.banco.repository;

import com.github.nogueiralegacy.construcao.AuxiliarTest;
import com.github.nogueiralegacy.construcao.domain.Projeto;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProjetoRepositoryTest {
    @Autowired
    AuxiliarTest auxiliarTest;
    @Autowired
    ProjetoRepository projetoRepository;

    Projeto projeto;
    @BeforeEach
    void setUp() {
        projeto = auxiliarTest.setUpProjeto();
    }

    @AfterEach
    void tearDown() {
        auxiliarTest.tearDownProjeto(projeto);
        projeto = null;
    }

    @Test
    void buscandoProjetosQueUmUsuarioParticipaPeloNickname() {
        Iterable<Projeto> projetosRetornados = projetoRepository.findByParticipantesNickname("testador");

        for (Projeto projetoRetornado : projetosRetornados) {
            assertEquals(projeto, projetoRetornado);
        }
    }

    @Test
    void buscandoParticipantesDeUmProjetoPeloId() {
        Iterable<Usuario> participantesRetornados = projetoRepository.findParticipantesByNomeProjeto("Projeto Teste");

        for (Usuario participanteRetornado : participantesRetornados) {
            assertEquals(projeto.getCriador(), participanteRetornado);
        }
    }
}
