package com.cssantana.recordz.diario.registrar;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistrarDiarioRequest(
        @NotNull(message = "O ID do usuário é obrigatório.")
        Long usuarioId,

        Long albumId,

        Long musicaId,

        LocalDateTime dataEscuta
) {}
