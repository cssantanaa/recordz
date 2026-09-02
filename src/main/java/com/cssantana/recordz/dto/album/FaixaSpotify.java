package com.cssantana.recordz.dto.album;

import lombok.*;

@Getter
@Setter
public class FaixaSpotify {

    private String idSpotify;
    private String nome;
    private Integer numeroFaixa;
    private Integer duracaoMs;
    private Boolean explicita;
}
