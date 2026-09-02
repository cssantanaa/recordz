package com.cssantana.recordz.repository;

import com.cssantana.recordz.model.AvaliacaoFaixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoFaixaRepository extends JpaRepository<AvaliacaoFaixa, Long> {

    Optional<AvaliacaoFaixa> findByUsuarioIdAndFaixaId(Long usuarioId, Long faixaId);

    boolean existsByUsuarioIdAndFaixaId(Long usuarioId, Long faixaId);

    List<AvaliacaoFaixa> findByFaixaIdOrderByDataAvaliacaoDesc(Long faixaId);
    List<AvaliacaoFaixa> findByUsuarioIdOrderByDataAvaliacaoDesc(Long usuarioId);
}