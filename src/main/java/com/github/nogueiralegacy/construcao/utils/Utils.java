package com.github.nogueiralegacy.construcao.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
    /**
     * Retorna o caminho (Path) para o recurso especificado pelo nome do arquivo.
     * O recurso é procurado usando o ClassLoader associado à classe atual.
     *
     * @param fileName o nome do arquivo do recurso a ser localizado.
     * @return o caminho (Path) para o recurso encontrado.
     * @throws IllegalArgumentException se o recurso não for encontrado.
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

    /**
     * Retorna as propriedades armazenadas em um arquivo especificado
     * pelo seu caminho (pathPropertiesFile)
     *
     * @param pathPropertiesFile o caminho do arquivo a ser localizado.
     * @return um objeto Properties com as propriedades contidas no arquivo
     * ou null caso o arquivo não for encontrado.
     *
     */
    public Properties getProperties(Path pathPropertiesFile) {
        Properties properties = new Properties();


        try (FileInputStream fileInputStream = new FileInputStream(pathPropertiesFile.toFile())) {
            properties.load(fileInputStream);
        } catch (IOException ioException) {
            return properties;
        }

        return properties;
    }
}
