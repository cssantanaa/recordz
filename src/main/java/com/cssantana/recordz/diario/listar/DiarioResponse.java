package com.cssantana.recordz.diario.listar;

import com.cssantana.recordz.diario.TipoRegistro;

import java.time.LocalDateTime;

public record DiarioResponse(
        Long id,
        LocalDateTime dataEscuta,
        TipoRegistro tipo,
        Long albumId,
        Long faixaId,
        String nome,
        String artista,
        String albumNome,
        String imagemUrl
) {}
