package com.cssantana.recordz.diario;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.musica.Musica;
import com.cssantana.recordz.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "registros_diario")
public class Diario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_escuta", nullable = false)
    private LocalDateTime dataEscuta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musica_id")
    private Musica musica;

    public static Diario criar(LocalDateTime dataEscuta, Usuario usuario, Album album, Musica musica) {
        Diario diario = new Diario();
        diario.dataEscuta = dataEscuta != null ? dataEscuta : LocalDateTime.now();
        diario.usuario = usuario;
        diario.album = album;
        diario.musica = musica;
        return diario;
    }
}
