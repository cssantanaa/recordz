package com.cssantana.recordz.dto.album;

import java.util.List;
import lombok.*;

@Getter
@Setter
public class AlbumSpotify {

    private String idSpotify;
    private String nome;
    private String artista;
    private String imagemUrl;
    private String dataLancamento;
    private Integer quantidadeFaixas;
    private String tipo;
    private List<FaixaSpotify> faixas;
}
