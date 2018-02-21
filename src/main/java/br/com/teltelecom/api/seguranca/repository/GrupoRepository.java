package br.com.teltelecom.api.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teltelecom.api.seguranca.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{

}
