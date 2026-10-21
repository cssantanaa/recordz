package com.cssantana.recordz.avaliacoes.album.excluir;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avaliacoes")
public class ExcluirAvaliacaoAlbumController {

    private final ExcluirAvaliacaoAlbumService service;

    public ExcluirAvaliacaoAlbumController(ExcluirAvaliacaoAlbumService service) {
        this.service = service;
    }

    @DeleteMapping("/{avaliacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long avaliacaoId) {
        service.executar(avaliacaoId);
    }
}