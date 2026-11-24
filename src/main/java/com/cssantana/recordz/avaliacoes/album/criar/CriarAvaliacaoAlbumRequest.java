package com.cssantana.recordz.avaliacoes.album.criar;

import com.cssantana.recordz.avaliacoes.Nota;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarAvaliacaoAlbumRequest(
        @NotNull(message = "A nota é obrigatória.")
        Nota nota,

        @Size(max = 2000, message = "O comentário deve ter no máximo 2000 caracteres.")
        String comentario
) {}