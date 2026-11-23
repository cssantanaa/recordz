package com.cssantana.recordz.avaliacoes.musica;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoMusicaRepository extends JpaRepository<AvaliacaoMusica, Long> {

    Optional<AvaliacaoMusica> findByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId);

    boolean existsByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId);

    List<AvaliacaoMusica> findByMusicaIdOrderByDataAvaliacaoDesc(Long musicaId);
}