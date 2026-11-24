package com.cssantana.recordz.avaliacoes.album.listar;

import com.cssantana.recordz.avaliacoes.Nota;
import java.time.LocalDateTime;

public record AvaliacaoAlbumResponse(
        Long id,
        String nomeUsuario,
        String fotoUsuario,
        Nota nota,
        String comentario,
        LocalDateTime dataAvaliacao
) {}
