package com.cssantana.recordz.avaliacoes.musica;

import com.cssantana.recordz.avaliacoes.Nota;
import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.musica.Musica;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(
        name = "avaliacoes_musica",
        uniqueConstraints = {
                @UniqueConstraint(name = "usuario_musica", columnNames = {"usuario_id", "musica_id"} )
        }
)
public class AvaliacaoMusica {

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
    @JoinColumn( name = "musica_id", nullable = false )
    private Musica musica;

    @PrePersist
    protected void aoCriar() {
        this.dataAvaliacao = LocalDateTime.now();
    }
}
