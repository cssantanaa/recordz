
package com.cssantana.recordz.usuario.listar;

import com.cssantana.recordz.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResponseMapper {

    public UsuarioResponse paraResponse(Usuario usuario) {

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getNomeUsuario(),
                usuario.getEmail(),
                usuario.getBiografia(),
                usuario.getFotoPerfil(),
                usuario.getDataCadastro()
        );
    }
}
