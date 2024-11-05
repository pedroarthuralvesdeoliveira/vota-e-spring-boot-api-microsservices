package br.com.fiap.vota_e.microsservice_sugestoes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "TBL_SUGESTOES")
@Data
public class Sugestao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    private String observacao;
    private String localizacao;
    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;
    @Column(name = "USUARIO_ID")
    private Long usuarioId;
}
