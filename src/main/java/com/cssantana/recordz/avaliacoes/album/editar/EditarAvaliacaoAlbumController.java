package com.cssantana.recordz.avaliacoes.album.editar;

import com.cssantana.recordz.avaliacoes.album.listar.AvaliacaoAlbumResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avaliacoes")
public class EditarAvaliacaoAlbumController {

    private final EditarAvaliacaoAlbumService service;

    public EditarAvaliacaoAlbumController(EditarAvaliacaoAlbumService service) {
        this.service = service;
    }

    @PutMapping("/{avaliacaoId}")
    public AvaliacaoAlbumResponse editar(@PathVariable Long avaliacaoId, @Valid @RequestBody EditarAvaliacaoAlbumRequest request) {
        return service.executar(avaliacaoId, request);
    }
}