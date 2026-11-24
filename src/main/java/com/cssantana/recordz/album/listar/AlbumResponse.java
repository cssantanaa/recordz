package com.cssantana.recordz.album.listar;

import com.cssantana.recordz.album.TipoAlbum;

public record AlbumResponse(
        Long id,
        String idSpotify,
        String nome,
        String artista,
        String imagemUrl,
        String dataLancamento,
        Integer quantidadeFaixas,
        TipoAlbum tipo,
        String spotifyUrl
) {}