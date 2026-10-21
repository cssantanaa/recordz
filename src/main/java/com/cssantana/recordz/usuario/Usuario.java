package com.cssantana.recordz.usuario;

import com.cssantana.recordz.avaliacoes.album.dominio.AvaliacaoAlbum;
import com.cssantana.recordz.avaliacoes.musica.AvaliacaoMusica;
import com.cssantana.recordz.diario.Diario;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Table(
        name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(name = "usuario_email", columnNames = "email"),
                @UniqueConstraint(name = "usuario_usuario", columnNames = "usuario")
        }
)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "nome_usuario", nullable = false, length = 50)
    private String nomeUsuario;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 500)
    private String biografia;

    @Column(name = "foto_perfil", length = 500)
    private String fotoPerfil;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "usuario")
    private List<AvaliacaoAlbum> avaliacoesAlbum = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<AvaliacaoMusica> avaliacoesFaixa = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Diario> registrosDiario = new ArrayList<>();

    @PrePersist
    protected void aoCriar() {
        this.dataCadastro = LocalDateTime.now();
    }
}
