
package com.cssantana.recordz.usuario.cadastrar;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CadastrarUsuarioService {

    private final UsuarioRepository repository;
    private final CadastrarUsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public CadastrarUsuarioService(
            UsuarioRepository repository,
            CadastrarUsuarioMapper mapper,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario executar(CadastrarUsuarioRequest request) {

        String email = request.email().trim().toLowerCase();
        String nomeUsuario = request.nomeUsuario().trim();

        if (repository.existsByEmail(email)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este e-mail já está cadastrado"
            );
        }

        if (repository.existsByNomeUsuario(nomeUsuario)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este nome de usuário já está em uso"
            );
        }

        String senhaCriptografada = passwordEncoder.encode(request.senha());

        CadastrarUsuarioRequest requestNormalizado =
                new CadastrarUsuarioRequest(
                        request.nome().trim(),
                        nomeUsuario,
                        email,
                        request.senha(),
                        request.biografia(),
                        request.fotoPerfil()
                );

        Usuario usuario = mapper.paraEntidade(
                requestNormalizado,
                senhaCriptografada
        );

        return repository.save(usuario);
    }
}