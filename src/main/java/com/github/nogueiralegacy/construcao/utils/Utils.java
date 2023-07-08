package com.github.nogueiralegacy.construcao.utils;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    /**
     * Retorna o caminho (Path) para o recurso especificado pelo nome do arquivo.
     * O recurso é procurado usando o ClassLoader associado à classe atual.
     *
     * @param fileName o nome do arquivo do recurso a ser localizado
     * @return o caminho (Path) para o recurso encontrado
     * @throws IllegalArgumentException se o recurso não for encontrado
     */
    public Path getPath(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            return Paths.get(resource.toString());
        } else {
            throw new IllegalArgumentException("Recurso não encntrado: " + fileName);
        }
    }

}
