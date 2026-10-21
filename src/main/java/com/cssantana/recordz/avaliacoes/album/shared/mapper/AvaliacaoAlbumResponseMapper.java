package com.cssantana.recordz.avaliacoes.album.shared.mapper;

import com.cssantana.recordz.avaliacoes.album.dominio.AvaliacaoAlbum;
import com.cssantana.recordz.avaliacoes.album.listar.AvaliacaoAlbumResponse;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoAlbumResponseMapper {

    public AvaliacaoAlbumResponse paraResponse(AvaliacaoAlbum avaliacao) {
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
