package com.cssantana.recordz.album;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByIdSpotify(String idSpotify);

    boolean existsByIdSpotify(String idSpotify);
}
