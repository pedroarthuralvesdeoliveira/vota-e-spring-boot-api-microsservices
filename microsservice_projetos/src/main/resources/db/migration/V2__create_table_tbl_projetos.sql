CREATE TABLE TBL_PROJETOS (
                              ID BIGINT(20) NOT NULL auto_increment,
                              DESCRICAO nvarchar(200) NOT NULL,
                              TITULO nvarchar(100) NOT NULL,
                              STATUS nvarchar(20) CHECK ( STATUS IN ('EM_ELABORACAO', 'ENVIADO', 'APROVADO', 'REJEITADO', 'CANCELADO') ) NOT NULL,
                              DATA_APROVACAO TIMESTAMP,
                              DATA_CANCELAMENTO TIMESTAMP,
                              DATA_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              DATA_ENVIO TIMESTAMP,
                              DATA_REJEICAO TIMESTAMP,
                              SUGESTAO_ID INTEGER NOT NULL,
                              primary key (ID),
                              CONSTRAINT FK_SUGESTAO_PROJETO
                                  FOREIGN KEY (SUGESTAO_ID)
                                      references db_sugestoes.TBL_SUGESTOES(ID)
);