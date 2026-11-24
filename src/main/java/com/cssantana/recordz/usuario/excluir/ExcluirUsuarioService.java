
package com.cssantana.recordz.usuario.excluir;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExcluirUsuarioService {

    private final UsuarioRepository repository;

    public ExcluirUsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void executar(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado(
                        "Usuário não encontrado"
                ));

        repository.delete(usuario);
    }
}
