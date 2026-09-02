package com.cssantana.recordz.mapper;

import com.cssantana.recordz.dto.avaliacao.AvaliacaoAlbumDto;
import com.cssantana.recordz.dto.avaliacao.AvaliacaoDto;
import com.cssantana.recordz.model.Album;
import com.cssantana.recordz.model.AvaliacaoAlbum;
import com.cssantana.recordz.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoAlbumMapper {

    public AvaliacaoAlbum paraEntidade(AvaliacaoAlbumDto dto, Usuario usuario, Album album) {

        return new AvaliacaoAlbum(
                dto.getNota(),
                dto.getComentario(),
                usuario,
                album
        );
    }

    public AvaliacaoDto paraDTO(AvaliacaoAlbum avaliacao) {

        AvaliacaoDto dto = new AvaliacaoDto();

        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setDataAvaliacao(avaliacao.getDataAvaliacao());

        if (avaliacao.getUsuario() != null) {
            dto.setNomeUsuario(
                    avaliacao.getUsuario().getNomeUsuario()
            );

            dto.setFotoUsuario(
                    avaliacao.getUsuario().getFotoPerfil()
            );
        }

        return dto;
    }
}