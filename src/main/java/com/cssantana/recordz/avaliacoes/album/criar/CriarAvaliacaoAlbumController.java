package com.cssantana.recordz.avaliacoes.album.criar;

import com.cssantana.recordz.avaliacoes.album.listar.AvaliacaoAlbumResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albuns/{albumId}/avaliacoes")
public class CriarAvaliacaoAlbumController {

    private final CriarAvaliacaoAlbumService service;

    public CriarAvaliacaoAlbumController(CriarAvaliacaoAlbumService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoAlbumResponse criar(@PathVariable Long albumId, @RequestParam Long usuarioId, @Valid @RequestBody CriarAvaliacaoAlbumRequest request) {
        return service.executar(albumId, usuarioId, request);
    }
}