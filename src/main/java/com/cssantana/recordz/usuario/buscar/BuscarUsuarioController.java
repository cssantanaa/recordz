
package com.cssantana.recordz.usuario.buscar;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.listar.UsuarioResponse;
import com.cssantana.recordz.usuario.listar.UsuarioResponseMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class BuscarUsuarioController {

    private final BuscarUsuarioService service;
    private final UsuarioResponseMapper mapper;

    public BuscarUsuarioController(
            BuscarUsuarioService service,
            UsuarioResponseMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscar(@PathVariable Long id) {

        Usuario usuario = service.executar(id);

        return mapper.paraResponse(usuario);
    }
}
