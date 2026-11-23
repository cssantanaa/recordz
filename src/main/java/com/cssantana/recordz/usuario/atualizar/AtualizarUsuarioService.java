
package com.cssantana.recordz.usuario.atualizar;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import com.cssantana.recordz.shared.exception.RegraNegocio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtualizarUsuarioService {

    private final UsuarioRepository repository;
    private final AtualizarUsuarioMapper mapper;

    public AtualizarUsuarioService(
            UsuarioRepository repository,
            AtualizarUsuarioMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public Usuario executar(
            Long id,
            AtualizarUsuarioRequest request
    ) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado(
                        "Usuário não encontrado"
                ));

        String email = request.email().trim().toLowerCase();
        String nomeUsuario = request.nomeUsuario().trim().toLowerCase();

        if (repository.existsByEmailAndIdNot(email, id)) {
            throw new RegraNegocio(
                    "Este e-mail já está cadastrado"
            );
        }

        if (repository.existsByNomeUsuarioAndIdNot(nomeUsuario, id)) {
            throw new RegraNegocio(
                    "Este nome de usuário já está em uso"
            );
        }

        mapper.atualizar(usuario, request);

        return repository.save(usuario);
    }
}
