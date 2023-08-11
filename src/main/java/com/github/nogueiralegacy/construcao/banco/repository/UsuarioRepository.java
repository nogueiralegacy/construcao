package com.github.nogueiralegacy.construcao.banco.repository;

import com.github.nogueiralegacy.construcao.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Interface para declarar, de forma simples, consultas (query)
 * ao banco de dados relacionadas a tabela Usuario.
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    /**
     * Busca um Usuario pelo seu username
     *
     * @param nickname username do usuario a ser buscado
     * @return usuario com o username informado
     */
    Optional<Usuario> findByNickname(String nickname);

    /**
     * Remove o usuario com o username informado
     *
     * @param nickname usuario a ser removido do banco
     * @return quantidades de linhas afetadas
     */
    Long deleteByNickname(String nickname);

    /**
     * Verifica se existe um usuario com o username informado
     * @param nickname username do usuario a ser verificado
     * @return true se existir um usuario com o username informado ou false caso contrario
     */
    boolean existsByNickname(String nickname);
}

