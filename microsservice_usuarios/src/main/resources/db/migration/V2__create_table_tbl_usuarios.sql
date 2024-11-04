CREATE TABLE TBL_USUARIOS (
                              ID INT NOT NULL AUTO_INCREMENT,
                              NOME VARCHAR(100) NOT NULL,
                              EMAIL VARCHAR(100) NOT NULL UNIQUE,
                              SENHA VARCHAR(100) NOT NULL,
                              TELEFONE VARCHAR(20) NOT NULL UNIQUE,
                              ROLE VARCHAR(50) DEFAULT 'USER',
                              PRIMARY KEY (ID)
);
