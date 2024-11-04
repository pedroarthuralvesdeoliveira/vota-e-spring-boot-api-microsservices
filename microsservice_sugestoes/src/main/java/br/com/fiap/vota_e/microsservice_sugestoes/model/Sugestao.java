package br.com.fiap.vota_e.microsservice_sugestoes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tbl_sugestoes")
@Data
public class Sugestao {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SUGESTOES"
    )
    @SequenceGenerator(
            name = "SEQ_SUGESTOES",
            sequenceName = "SEQ_SUGESTOES",
            allocationSize = 1
    )
    private Long id;
    private String descricao;
    private String observacao;
    private String localizacao;
    private Date dataCriacao;
    private Long usuarioId;
}
