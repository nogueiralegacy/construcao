package com.github.nogueiralegacy.construcao.service;

import com.github.nogueiralegacy.construcao.banco.orm.Projeto;
import com.github.nogueiralegacy.construcao.banco.repository.ProjetoRepository;
import com.github.nogueiralegacy.construcao.utils.Utils;
import com.github.nogueiralegacy.construcao.utils.dto.ProjetoDTO;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {
    private final UsuarioService usuarioService;
    private final ProjetoRepository projetoRepository;

    public ProjetoService(UsuarioService usuarioService, ProjetoRepository projetoRepository) {
        this.usuarioService = usuarioService;
        this.projetoRepository = projetoRepository;
    }

    public void saveProjeto(ProjetoDTO projetoDTO) throws IllegalArgumentException {
        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.nome());
        projeto.setDescricao(projetoDTO.descricao());
        projeto.setDataCriacao(Utils.toLocalDateTime(projetoDTO.dataCriacao()));
        projeto.setCriador(usuarioService.findByNickname(projetoDTO.criadorNickname()));
        projeto.setParticipantes(usuarioService.findByNicknames(projetoDTO.participantesNicknames()));

        projetoRepository.save(projeto);
    }
}
