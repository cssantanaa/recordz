package com.cssantana.recordz.dto.avaliacao;

import com.cssantana.recordz.model.Nota;
import jakarta.validation.constraints.*;

public class AvaliacaoFaixa {

    @NotNull(message = "A nota é obrigatória.")
    private Nota nota;

    @Size(max = 2000, message = "O comentário deve ter no máximo 2000 caracteres.")
    private String comentario;

}