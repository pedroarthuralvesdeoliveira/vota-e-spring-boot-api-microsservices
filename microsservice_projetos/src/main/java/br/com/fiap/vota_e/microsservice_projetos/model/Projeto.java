package br.com.fiap.vota_e.microsservice_projetos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tbl_projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Projeto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PROJETOS"
    )
    @SequenceGenerator(
            name = "SEQ_PROJETOS",
            sequenceName = "SEQ_PROJETOS",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;
    private String descricao;
    private String titulo;
    private String status;
    private Date dataCadastro;
    private Date dataEnvio;
    private Date dataAprovacao;
    private Long sugestaoId;
}
