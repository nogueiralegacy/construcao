package com.github.nogueiralegacy.construcao.service;

import com.github.nogueiralegacy.construcao.domain.Projeto;
import com.github.nogueiralegacy.construcao.banco.repository.ProjetoRepository;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.utils.Utils;
import com.github.nogueiralegacy.construcao.utils.dto.ProjetoDTO;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;
import java.util.Optional;

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

        if (projetoExistsByNome(projetoDTO.nome())) {
            throw new IllegalArgumentException("Já existe um projeto com esse nome");
        }

        String projetoNome = projetoDTO.nome();
        Usuario projetoCriador = usuarioService.findByNickname(projetoDTO.criadorNickname());

        Projeto projeto = new Projeto(projetoNome, projetoCriador);

        projeto.setDescricao(projetoDTO.descricao());
        try {
            projeto.setDataCriacao(Utils.toLocalDateTime(projetoDTO.dataCriacao()));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido, o formato correto é 'yyyy-MM-dd HH:mm:ss'");
        }
        projeto.setParticipantes(usuarioService.findByNicknames(projetoDTO.participantesNicknames()));

        projetoRepository.save(projeto);
    }

    private boolean isProjetoValido(ProjetoDTO projetoDTO) {
        return projetoDTO != null && projetoDTO.nome() != null && projetoDTO.criadorNickname() != null;
    }

    public Iterable<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public  boolean projetoExistsByNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O parametro 'nome' não pode ser nulo");
        }

        return projetoRepository.existsByNome(nome);
    }

    public void addParticipante(Long idProjeto, String nickname) throws IllegalArgumentException {
        Projeto projeto = findById(idProjeto);
        Usuario participante = usuarioService.findByNickname(nickname);

        projeto.addParticipante(participante);

        projetoRepository.save(projeto);
    }

    private Projeto findById(Long idProjeto) throws IllegalArgumentException {
        if (idProjeto == null) {
            throw new IllegalArgumentException("O parametro 'id_projeto' não pode ser nulo");
        }

        Optional<Projeto> projeto = projetoRepository.findById(idProjeto);

        if (projeto.isPresent()) {
            return projeto.get();
        }

        throw new IllegalArgumentException("Projeto não encotrado. Id: " + idProjeto);

    }
}
