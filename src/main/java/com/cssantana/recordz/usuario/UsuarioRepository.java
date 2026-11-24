package com.cssantana.recordz.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByNomeUsuario(String nomeUsuario);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByNomeUsuarioAndIdNot(String nomeUsuario, Long id);
}