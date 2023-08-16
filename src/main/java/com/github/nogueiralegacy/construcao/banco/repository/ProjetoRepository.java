package com.github.nogueiralegacy.construcao.banco.repository;

import com.github.nogueiralegacy.construcao.domain.Projeto;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjetoRepository extends CrudRepository<Projeto, Long> {
    Iterable<Projeto> findByParticipantesNickname(String nickname);

    @Query("SELECT p.participantes FROM Projeto p WHERE p.nome = :nomeProjeto")
    Iterable<Usuario> findParticipantesByNomeProjeto(@Param("nomeProjeto") String nomeProjeto);

    boolean existsByNome(String nome);
}
