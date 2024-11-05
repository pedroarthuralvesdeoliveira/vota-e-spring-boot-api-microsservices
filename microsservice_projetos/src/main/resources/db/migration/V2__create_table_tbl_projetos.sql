CREATE TABLE TBL_PROJETOS (
                              ID BIGINT NOT NULL AUTO_INCREMENT,
                              DESCRICAO VARCHAR(200) NOT NULL,
                              TITULO VARCHAR(100) NOT NULL,
                              STATUS VARCHAR(20) NOT NULL,
                              DATA_APROVACAO TIMESTAMP NULL,
                              DATA_CANCELAMENTO TIMESTAMP NULL,
                              DATA_CRIACAO TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              DATA_ENVIO TIMESTAMP NULL,
                              DATA_REJEICAO TIMESTAMP NULL,
                              SUGESTAO_ID INTEGER NOT NULL,
                              PRIMARY KEY (ID),
                              CONSTRAINT FK_SUGESTAO_PROJETO
                                  FOREIGN KEY (SUGESTAO_ID)
                                      REFERENCES db_sugestoes.TBL_SUGESTOES(ID),
                              CONSTRAINT CHK_STATUS CHECK (STATUS IN ('EM_ELABORACAO', 'ENVIADO', 'APROVADO', 'REJEITADO', 'CANCELADO'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;