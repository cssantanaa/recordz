package com.cssantana.recordz.usuario.atualizar;

import com.cssantana.recordz.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AtualizarUsuarioMapper {

    public void atualizar(
            Usuario usuario,
            AtualizarUsuarioRequest request
    ) {
        usuario.atualizar(
                request.nome().trim(),
                request.nomeUsuario().trim().toLowerCase(),
                request.email().trim().toLowerCase(),
                request.biografia(),
                request.fotoPerfil()
        );
    }
}
