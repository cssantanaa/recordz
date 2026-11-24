
package com.cssantana.recordz.usuario.cadastrar;

import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.listar.UsuarioResponse;
import com.cssantana.recordz.usuario.listar.UsuarioResponseMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class CadastrarUsuarioController {

    private final CadastrarUsuarioService service;
    private final UsuarioResponseMapper responseMapper;

    public CadastrarUsuarioController(
            CadastrarUsuarioService service,
            UsuarioResponseMapper responseMapper
    ) {
        this.service = service;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(
            @Valid @RequestBody CadastrarUsuarioRequest request
    ) {
        Usuario usuario = service.executar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseMapper.paraResponse(usuario));
    }
}