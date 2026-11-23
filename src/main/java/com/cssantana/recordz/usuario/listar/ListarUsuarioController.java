
package com.cssantana.recordz.usuario.listar;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ListarUsuarioController {

    private final ListarUsuarioService service;
    private final UsuarioResponseMapper mapper;

    public ListarUsuarioController(
            ListarUsuarioService service,
            UsuarioResponseMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UsuarioResponse> listar() {

        return service.executar()
                .stream()
                .map(mapper::paraResponse)
                .toList();
    }
}
