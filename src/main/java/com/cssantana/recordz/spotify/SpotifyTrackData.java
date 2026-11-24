package com.cssantana.recordz.spotify;

public record SpotifyTrackData(
        String id,
        String nome,
        Integer numeroFaixa,
        Integer duracaoMs,
        Boolean explicita
) {}
