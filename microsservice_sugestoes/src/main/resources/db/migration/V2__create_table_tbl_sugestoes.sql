CREATE TABLE TBL_SUGESTOES (
                               ID INTEGER  NOT NULL auto_increment,
                               DESCRICAO VARCHAR (100) NOT NULL,
                               OBSERVACAO VARCHAR (50) NULL,
                               LOCALIZACAO VARCHAR (125) NULL,
                               DATA_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                               USUARIO_ID INTEGER NOT NULL,
                               primary key (ID)
);