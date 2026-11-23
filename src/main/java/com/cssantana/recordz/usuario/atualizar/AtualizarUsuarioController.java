package com.cssantana.recordz.usuario.atualizar;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.listar.UsuarioResponse;
import com.cssantana.recordz.usuario.listar.UsuarioResponseMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class AtualizarUsuarioController {

    private final AtualizarUsuarioService service;
    private final UsuarioResponseMapper mapper;

    public AtualizarUsuarioController(
            AtualizarUsuarioService service,
            UsuarioResponseMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarUsuarioRequest request
    ) {

        Usuario usuario = service.executar(id, request);

        return mapper.paraResponse(usuario);
    }
}
