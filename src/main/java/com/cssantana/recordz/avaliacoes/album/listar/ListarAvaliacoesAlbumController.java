package com.cssantana.recordz.avaliacoes.album.listar;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albuns/{albumId}/avaliacoes")
public class ListarAvaliacoesAlbumController {

    private final ListarAvaliacoesAlbumService service;

    public ListarAvaliacoesAlbumController(ListarAvaliacoesAlbumService service) {
        this.service = service;
    }

    @GetMapping
    public List<AvaliacaoAlbumResponse> listar(@PathVariable Long albumId) {
        return service.executar(albumId);
    }
}