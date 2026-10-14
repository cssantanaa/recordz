package com.cssantana.recordz.musica;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    Optional<Musica> findByIdSpotify(String idSpotify);

    boolean existsByIdSpotify(String idSpotify);

    List<Musica> findByAlbumIdOrderByNumeroFaixaAsc(Long albumId);
}