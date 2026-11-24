package com.cssantana.recordz.diario.listar;

import com.cssantana.recordz.diario.Diario;
import com.cssantana.recordz.diario.TipoRegistro;
import org.springframework.stereotype.Component;

@Component
public class DiarioResponseMapper {

    public DiarioResponse paraResponse(Diario diario) {
        if (diario.getMusica() != null) {
            var musica = diario.getMusica();
            var album = musica.getAlbum();

            return new DiarioResponse(
                    diario.getId(),
                    diario.getDataEscuta(),
                    TipoRegistro.FAIXA,
                    album.getId(),
                    musica.getId(),
                    musica.getNome(),
                    album.getArtista(),
                    album.getNome(),
                    album.getImagemUrl()
            );
        }

        var album = diario.getAlbum();

        return new DiarioResponse(
                diario.getId(),
                diario.getDataEscuta(),
                TipoRegistro.ALBUM,
                album.getId(),
                null,
                album.getNome(),
                album.getArtista(),
                album.getNome(),
                album.getImagemUrl()
        );
    }
}
