package com.cssantana.recordz.dto.avaliacao;

import com.cssantana.recordz.model.Nota;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class AvaliacaoDto {

    private Long id;
    private String nomeUsuario;
    private String fotoUsuario;
    private Nota nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

}
