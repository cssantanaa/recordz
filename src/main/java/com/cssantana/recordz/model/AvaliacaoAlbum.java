package com.cssantana.recordz.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(
        name = "avaliacoes_album",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "usuario_album",
                        columnNames = {"usuario_id", "album_id"}
                )
        }
)
public class AvaliacaoAlbum {

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
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public AvaliacaoAlbum(
            Nota nota,
            String comentario,
            Usuario usuario,
            Album album
    ) {
        this.nota = nota;
        this.comentario = comentario;
        this.usuario = usuario;
        this.album = album;
    }

    @PrePersist
    protected void aoCriar() {
        this.dataAvaliacao = LocalDateTime.now();
    }

    public void atualizar(Nota nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
    }
}