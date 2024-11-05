package br.com.fiap.vota_e.microsservice_projetos.repository;

import br.com.fiap.vota_e.microsservice_projetos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByDataCriacaoBetween(Date dataInicial, Date dataFinal);
}
