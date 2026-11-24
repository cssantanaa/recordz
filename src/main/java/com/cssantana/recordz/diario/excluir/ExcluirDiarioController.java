package com.cssantana.recordz.diario.excluir;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diario")
public class ExcluirDiarioController {

    private final ExcluirDiarioService service;

    public ExcluirDiarioController(ExcluirDiarioService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.executar(id);
    }
}
