
package com.cssantana.recordz.usuario.buscar;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioService {

    private final UsuarioRepository repository;

    public BuscarUsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario executar(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado(
                        "Usuário não encontrado"
                ));
    }
}
