package com.cssantana.recordz.album;

import com.cssantana.recordz.avaliacoes.album.dominio.AvaliacaoAlbum;
import com.cssantana.recordz.diario.Diario;
import com.cssantana.recordz.musica.Musica;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Table(
        name = "albuns",
        uniqueConstraints = {@UniqueConstraint(name = "album_id_spotify", columnNames = "id_spotify")}
)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_spotify", nullable = false, unique = true)
    private String idSpotify;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 200)
    private String artista;

    @Column(name = "imagem_url", length = 500)
    private String imagemUrl;

    @Column(name = "data_lancamento")
    private String dataLancamento;

    @Column(name = "quantidade_faixas")
    private Integer quantidadeFaixas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoAlbum tipo;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Musica> musicas = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    private List<AvaliacaoAlbum> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    private List<Diario> registrosDiario = new ArrayList<>();

}
