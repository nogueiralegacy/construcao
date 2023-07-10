package com.github.nogueiralegacy.construcao.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
    /**
     * Retorna caminho de um arquivo no diretório resources.
     *
     * @param fileName O nome do arquivo desejado no resources.
     *
     * @return Retorna o caminho para o arquivo no diretório resources.
     */
    public Path getPath(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        URI uri = null;

        try {
            uri = resource.toURI();
        } catch (Exception exp) {
            return null;
        }

        return Paths.get(uri);
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
    public static Properties getProperties(Path pathPropertiesFile) {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(pathPropertiesFile.toFile())) {
            properties.load(fileInputStream);
        } catch (Exception exception) {
            return properties;
        }

        return properties;
    }
}
