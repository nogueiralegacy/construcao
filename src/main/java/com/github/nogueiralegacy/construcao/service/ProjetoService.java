package com.github.nogueiralegacy.construcao.service;

import com.github.nogueiralegacy.construcao.banco.orm.Projeto;
import com.github.nogueiralegacy.construcao.banco.repository.ProjetoRepository;
import com.github.nogueiralegacy.construcao.utils.Utils;
import com.github.nogueiralegacy.construcao.utils.dto.ProjetoDTO;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;

@Service
public class ProjetoService {
    private final UsuarioService usuarioService;
    private final ProjetoRepository projetoRepository;

    public ProjetoService(UsuarioService usuarioService, ProjetoRepository projetoRepository) {
        this.usuarioService = usuarioService;
        this.projetoRepository = projetoRepository;
    }

    public void saveProjeto(ProjetoDTO projetoDTO) throws IllegalArgumentException {
        if (!isProjetoValido(projetoDTO)) {
            throw new IllegalArgumentException("Formato de projeto inválido, os campos 'nome' e 'criadorNickname' são obrigatórios");
        }
        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.nome());
        projeto.setDescricao(projetoDTO.descricao());
        try {
            projeto.setDataCriacao(Utils.toLocalDateTime(projetoDTO.dataCriacao()));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido, o formato correto é 'yyyy-MM-dd HH:mm:ss'");
        }
        projeto.setCriador(usuarioService.findByNickname(projetoDTO.criadorNickname()));
        projeto.setParticipantes(usuarioService.findByNicknames(projetoDTO.participantesNicknames()));

        projetoRepository.save(projeto);
    }

    private boolean isProjetoValido(ProjetoDTO projetoDTO) {
        return projetoDTO != null && projetoDTO.nome() != null && projetoDTO.criadorNickname() != null;
    }
}
