package com.cssantana.recordz.dto.album;

import com.cssantana.recordz.dto.faixas.FaixaDetalhes;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class AlbumDetalhes {

    private Long id;
    private String idSpotify;
    private String nome;
    private String artista;
    private String imagemUrl;
    private String dataLancamento;
    private Integer quantidadeFaixas;
    private String tipo;
    private Double mediaAvaliacoes;
    private List<FaixaDetalhes> faixas;
}
