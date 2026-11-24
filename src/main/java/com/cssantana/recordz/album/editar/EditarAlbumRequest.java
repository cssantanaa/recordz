package com.cssantana.recordz.album.editar;

import com.cssantana.recordz.album.TipoAlbum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record EditarAlbumRequest(
        @NotBlank @Size(max = 200) String nome,
        @NotBlank @Size(max = 200) String artista,
        @Size(max = 500) String imagemUrl,
        String dataLancamento,
        @PositiveOrZero Integer quantidadeFaixas,
        @NotNull TipoAlbum tipo
) {}
