package com.cssantana.recordz.dto.faixas;

import com.cssantana.recordz.dto.avaliacao.AvaliacaoDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class FaixaDetalhes {

    private Long id;
    private String idSpotify;
    private String nome;
    private Integer numeroFaixa;
    private Integer duracaoMs;
    private Boolean explicita;
    private Long albumId;
    private String albumNome;
    private String albumImagemUrl;
    private String artista;
    private Double mediaAvaliacoes;

    private List<AvaliacaoDto> avaliacoes;

}