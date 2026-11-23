package com.cssantana.recordz.musica;

import com.cssantana.recordz.avaliacoes.musica.AvaliacaoMusicaResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class MusicaDetalhes {

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

    private List<AvaliacaoMusicaResponse> avaliacoes;

}