package com.github.nogueiralegacy.construcao.banco.entities;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface para declarar, de forma simples, consultas (query)
 * ao banco de dados relacionadas a tabela Usuario.
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    /**
     * Busca um Usuario pelo seu username
     *
     * @param username username do usuario a ser buscado
     * @return usuario com o username informado
     */
    Usuario findByUsername(String username);

    /**
     * Remove o usuario com o username informado
     *
     * @param username usuario a ser removido do banco
     * @return quantidades de linhas afetadas
     */
    Long deleteByUsername(String username);
}

