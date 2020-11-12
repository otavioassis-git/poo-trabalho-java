Para compilar, basta abrir a pasta src em seu terminal e compliar todos os arquivos da pasta interfaceG, classesDasInterfaces e leituraEscritaArquivo, usando o comando:
javac interfaceG/*.java classesDasInterfaces/*.java leituraEscritaArquivo/*.java

Para rodar o programa, após compilar, basta, com a pasta src aberta no terminal, usar o comando:
java interfaceG.TelaPrincipal

Ou, se preferir compilar usando o Ant, basta abrir o terminal na pasta principal e rodar o comando:
ant compile

Com isso, sera criada uma pasta bin-ant com os arquivos .class e, para rodar o programa, basta abrir seu terminal na pasta bin-ant e usar o comando:
java interfaceG.TelaPrincipal

Ou, para rodar o programa ja compilado anteriormente basta abrir a pasta bin no terminal e usar o comando:
java interfaceG.TelaPrincipal
