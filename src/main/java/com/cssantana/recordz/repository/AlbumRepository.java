package com.cssantana.recordz.repository;

import com.cssantana.recordz.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByIdSpotify(String idSpotify);

    boolean existsByIdSpotify(String idSpotify);
}
