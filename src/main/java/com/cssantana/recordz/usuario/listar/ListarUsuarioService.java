
package com.cssantana.recordz.usuario.listar;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUsuarioService {

    private final UsuarioRepository repository;

    public ListarUsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> executar() {
        return repository.findAll();
    }
}
