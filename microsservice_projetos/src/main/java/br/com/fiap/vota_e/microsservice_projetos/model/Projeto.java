package br.com.fiap.vota_e.microsservice_projetos.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "TBL_PROJETOS")
@Data
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String titulo;
    @Column(name = "DATA_APROVACAO")
    private Timestamp dataAprovacao;
    @Column(name = "DATA_CANCELAMENTO")
    private Timestamp dataCancelamento;
    @Column(name = "DATA_CRIACAO")
    private Timestamp dataCriacao;
    @Column(name = "DATA_ENVIO")
    private Timestamp dataEnvio;
    @Column(name = "DATA_REJEICAO")
    private Timestamp dataRejeicao;
    @Column(name = "SUGESTAO_ID", nullable = false)
    private Long sugestaoId;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;
}
