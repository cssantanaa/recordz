package com.cssantana.recordz.musica;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.avaliacoes.musica.AvaliacaoMusica;
import com.cssantana.recordz.diario.Diario;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "faixas",
        uniqueConstraints = {
                @UniqueConstraint(name = "faixa_id_spotify", columnNames = "id_spotify")
        }
)
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_spotify", nullable = false, unique = true)
    private String idSpotify;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "numero_faixa")
    private Integer numeroFaixa;

    @Column(name = "duracao_ms")
    private Integer duracaoMs;

    @Column(nullable = false)
    private Boolean explicita = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @OneToMany(mappedBy = "musica")
    private List<AvaliacaoMusica> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "musica")
    private List<Diario> registrosDiario = new ArrayList<>();

    public static Musica criar(String idSpotify, String nome, Integer numeroFaixa, Integer duracaoMs, Boolean explicita, Album album) {
        Musica musica = new Musica();
        musica.idSpotify = idSpotify;
        musica.nome = nome;
        musica.numeroFaixa = numeroFaixa;
        musica.duracaoMs = duracaoMs;
        musica.explicita = explicita != null && explicita;
        musica.album = album;
        return musica;
    }
}
