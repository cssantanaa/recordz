
package com.cssantana.recordz.usuario.listar;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nome,
        String nomeUsuario,
        String email,
        String biografia,
        String fotoPerfil,
        LocalDateTime dataCadastro
) {}
