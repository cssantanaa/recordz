package com.cssantana.recordz.diario.listar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diario")
public class ListarDiarioController {

    private final ListarDiarioService service;

    public ListarDiarioController(ListarDiarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<DiarioResponse> listar(@RequestParam Long usuarioId) {
        return service.executar(usuarioId);
    }
}
