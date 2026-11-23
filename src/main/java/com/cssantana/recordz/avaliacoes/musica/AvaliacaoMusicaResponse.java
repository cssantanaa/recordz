package com.cssantana.recordz.avaliacoes.musica;

import com.cssantana.recordz.avaliacoes.Nota;

import java.time.LocalDateTime;

public record AvaliacaoMusicaResponse(
        Long id,
        String nomeUsuario,
        Nota nota,
        String comentario,
        LocalDateTime dataAvaliacao
) {
}
