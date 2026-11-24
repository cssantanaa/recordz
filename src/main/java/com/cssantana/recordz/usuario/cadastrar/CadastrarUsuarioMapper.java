
package com.cssantana.recordz.usuario.cadastrar;

import com.cssantana.recordz.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CadastrarUsuarioMapper {

    public Usuario paraEntidade(
            CadastrarUsuarioRequest request,
            String senhaCriptografada
    ) {
        return Usuario.criar(
                request.nome(),
                request.nomeUsuario(),
                request.email(),
                senhaCriptografada,
                request.biografia(),
                request.fotoPerfil()
        );
    }
}
