package com.cssantana.recordz.avaliacoes.musica;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoMusicaRepository extends JpaRepository<AvaliacaoMusica, Long> {

    Optional<AvaliacaoMusica> findByUsuarioIdAndFaixaId(Long usuarioId, Long faixaId);

    boolean existsByUsuarioIdAndFaixaId(Long usuarioId, Long faixaId);

    List<AvaliacaoMusica> findByFaixaIdOrderByDataAvaliacaoDesc(Long faixaId);
    List<AvaliacaoMusica> findByUsuarioIdOrderByDataAvaliacaoDesc(Long usuarioId);
}