package com.cssantana.recordz.avaliacoes.album.criar;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.avaliacoes.album.dominio.AvaliacaoAlbum;
import com.cssantana.recordz.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CriarAvaliacaoAlbumMapper {

    public AvaliacaoAlbum paraEntidade(CriarAvaliacaoAlbumRequest request, Usuario usuario, Album album) {
        return new AvaliacaoAlbum(request.nota(), request.comentario(), usuario, album);
    }
}