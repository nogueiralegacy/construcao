package com.github.nogueiralegacy.construcao;

import com.github.nogueiralegacy.construcao.banco.repository.EtapaRepository;
import com.github.nogueiralegacy.construcao.banco.repository.ProjetoRepository;
import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import com.github.nogueiralegacy.construcao.domain.Etapa;
import com.github.nogueiralegacy.construcao.domain.Projeto;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.domain.UsuarioRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuxiliarTest {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private EtapaRepository etapaRepository;

    public Usuario setUpUsuario() {
        Usuario usuario = new Usuario();

        usuario.setNickname("testador");
        usuario.setPassword("test123");
        usuario.setNome("Testando");
        usuario.setEmail("test@gmail.com");
        usuario.setRole(UsuarioRole.USER);

        return usuarioRepository.save(usuario);
    }

    public void tearDownUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Etapa setUpEtapa() {
        Etapa etapa = new Etapa("Etapa Teste");
        return etapaRepository.save(etapa);
    }

    public void tearDownEtapa(Etapa etapa) {
        etapaRepository.delete(etapa);
    }

    public Projeto setUpProjeto() {
        String projetoNome = "Projeto Teste";
        Usuario projetoCriador = setUpUsuario();
        Projeto projeto = new Projeto(projetoNome, projetoCriador);


        projeto.setDescricao("Projeto para testes");
        projeto.setDataCriacao(LocalDateTime.now());
        projeto.addParticipante(setUpUsuario());
        projeto.addEtapa(setUpEtapa());

        return projetoRepository.save(projeto);
    }

    public void tearDownProjeto(Projeto projeto) {
        projetoRepository.delete(projeto);
    }
}
