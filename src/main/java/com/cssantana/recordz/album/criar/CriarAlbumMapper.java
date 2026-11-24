package com.cssantana.recordz.album.criar;

import com.cssantana.recordz.album.Album;
import org.springframework.stereotype.Component;

@Component
public class CriarAlbumMapper {
    public Album paraEntidade(CriarAlbumRequest request) {
        return Album.criar(
                request.idSpotify(), request.nome(), request.artista(),
                request.imagemUrl(), request.dataLancamento(),
                request.quantidadeFaixas(), request.tipo()
        );
    }
}