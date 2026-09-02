package com.cssantana.recordz.repository;

import com.cssantana.recordz.model.Faixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FaixaRepository extends JpaRepository<Faixa, Long> {

    Optional<Faixa> findByIdSpotify(String idSpotify);

    boolean existsByIdSpotify(String idSpotify);

    List<Faixa> findByAlbumIdOrderByNumeroFaixaAsc(Long albumId);
}