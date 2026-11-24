package com.cssantana.recordz.spotify;

public record SpotifyAlbumData(
        String id,
        String nome,
        String artista,
        String imagemUrl,
        String dataLancamento,
        Integer quantidadeFaixas,
        String tipo,
        String spotifyUrl
) {}
