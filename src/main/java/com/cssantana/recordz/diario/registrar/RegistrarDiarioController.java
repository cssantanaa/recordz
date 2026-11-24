package com.cssantana.recordz.diario.registrar;

import com.cssantana.recordz.diario.listar.DiarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diario")
public class RegistrarDiarioController {

    private final RegistrarDiarioService service;

    public RegistrarDiarioController(RegistrarDiarioService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiarioResponse registrar(@Valid @RequestBody RegistrarDiarioRequest request) {
        return service.executar(request);
    }
}
