package br.com.teltelecom.api.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teltelecom.api.seguranca.entity.Acao;

public interface AcaoRepository extends JpaRepository<Acao, Long>{

}
