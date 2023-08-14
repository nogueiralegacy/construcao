#!/bin/bash

# Criação de um arquivo temporário para armazenar a chave privada
echo $SSH_PRIVATE_KEY > id_rsa
chmod 400 key.pem

# Início da conexão SSH e execução do comando para iniciar o JAR
ssh -i id_rsa root@157.230.84.81 "nohup java -jar /root/aplicacao/target/construcao-1.0.jar > output.log 2>&1 &"

# Remoção do arquivo temporário
rm id_rsa
