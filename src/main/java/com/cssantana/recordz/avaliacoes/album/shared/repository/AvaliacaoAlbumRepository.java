package com.cssantana.recordz.avaliacoes.album.shared.repository;

import com.cssantana.recordz.avaliacoes.album.dominio.AvaliacaoAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoAlbumRepository extends JpaRepository<AvaliacaoAlbum, Long> {

    boolean existsByUsuarioIdAndAlbumId(Long usuarioId, Long albumId);

    List<AvaliacaoAlbum> findByAlbumId(Long albumId);
}