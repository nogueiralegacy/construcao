package com.github.nogueiralegacy.construcao.banco.repository;

import com.github.nogueiralegacy.construcao.banco.orm.Usuario;
import org.springframework.data.repository.CrudRepository;

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
    Usuario findByNickname(String nickname);

    /**
     * Remove o usuario com o username informado
     *
     * @param nickname usuario a ser removido do banco
     * @return quantidades de linhas afetadas
     */
    Long deleteByNickname(String nickname);
}

