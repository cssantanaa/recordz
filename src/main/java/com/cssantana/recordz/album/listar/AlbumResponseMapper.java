package com.cssantana.recordz.album.listar;

import com.cssantana.recordz.album.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumResponseMapper {
    public AlbumResponse paraResponse(Album album) {
        return new AlbumResponse(
                album.getId(), album.getIdSpotify(), album.getNome(),
                album.getArtista(), album.getImagemUrl(), album.getDataLancamento(),
                album.getQuantidadeFaixas(), album.getTipo(),
                "https://open.spotify.com/album/" + album.getIdSpotify()
        );
    }
}
