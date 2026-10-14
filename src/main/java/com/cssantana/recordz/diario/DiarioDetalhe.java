package com.cssantana.recordz.diario;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class DiarioDetalhe {

    private Long id;
    private LocalDateTime dataEscuta;
    private TipoRegistro tipo;
    private Long albumId;
    private Long faixaId;
    private String nome;
    private String artista;
    private String albumNome;
    private String imagemUrl;
}
