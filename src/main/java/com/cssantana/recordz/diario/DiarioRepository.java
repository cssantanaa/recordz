package com.cssantana.recordz.diario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiarioRepository extends JpaRepository<Diario, Long> {

    List<Diario> findByUsuarioIdOrderByDataEscutaDesc(Long usuarioId);
    List<Diario> findByAlbumIdOrderByDataEscutaDesc(Long albumId);

    List<Diario> findByFaixaIdOrderByDataEscutaDesc(Long faixaId);
}