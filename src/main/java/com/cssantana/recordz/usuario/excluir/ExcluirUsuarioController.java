package com.cssantana.recordz.usuario.excluir;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class ExcluirUsuarioController {

    private final ExcluirUsuarioService service;

    public ExcluirUsuarioController(ExcluirUsuarioService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        service.executar(id);

        return ResponseEntity.noContent().build();
    }
}
