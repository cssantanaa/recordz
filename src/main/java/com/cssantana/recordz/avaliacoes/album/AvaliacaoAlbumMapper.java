
package com.cssantana.recordz.avaliacoes.album;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoAlbumMapper {

    public AvaliacaoAlbum paraEntidade(AvaliacaoAlbumRequest request, Usuario usuario, Album album) {
        return new AvaliacaoAlbum(
                request.nota(),
                request.comentario(),
                usuario,
                album
        );
    }

    public AvaliacaoAlbumResponse paraResponse(
            AvaliacaoAlbum avaliacao
    ) {
        String nomeUsuario = null;
        String fotoUsuario = null;

        if (avaliacao.getUsuario() != null) {
            nomeUsuario = avaliacao.getUsuario().getNomeUsuario();
            fotoUsuario = avaliacao.getUsuario().getFotoPerfil();
        }

        return new AvaliacaoAlbumResponse(
                avaliacao.getId(),
                nomeUsuario,
                fotoUsuario,
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao()
        );
    }
}
