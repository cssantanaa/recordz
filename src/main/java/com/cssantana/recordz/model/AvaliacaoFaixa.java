package com.cssantana.recordz.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(
        name = "avaliacoes_faixa",
        uniqueConstraints = {
                @UniqueConstraint(name = "usuario_faixa", columnNames = {"usuario_id", "faixa_id"} )
        }
)
public class AvaliacaoFaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nota nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "data_avaliacao", nullable = false)
    private LocalDateTime dataAvaliacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false )
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "faixa_id", nullable = false )
    private Faixa faixa;

    @PrePersist
    protected void aoCriar() {
        this.dataAvaliacao = LocalDateTime.now();
    }
}
