#!/bin/bash

# Criação de um arquivo temporário para armazenar a chave privada
echo "${{ secrets.SSH_PRIVATE_KEY }}" > key.pem
chmod 400 key.pem

# Início da conexão SSH e execução do comando para iniciar o JAR
ssh -i key.pem -o StrictHostKeyChecking=no root@157.230.84.81 "nohup java -jar /root/aplicacao/construcao-1.0.jar > output.log 2>&1 &"

# Remoção do arquivo temporário
rm key.pem
