package com.cssantana.recordz.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Table(
        name = "faixas",
        uniqueConstraints = {
                @UniqueConstraint(name = "faixa_id_spotify", columnNames = "id_spotify")
        }
)
public class Faixa {

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

    @OneToMany(mappedBy = "faixa")
    private List<AvaliacaoFaixa> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "faixa")
    private List<Diario> registrosDiario = new ArrayList<>();

}
