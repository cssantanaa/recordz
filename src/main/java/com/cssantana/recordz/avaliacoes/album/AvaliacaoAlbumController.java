
package com.cssantana.recordz.avaliacoes.album;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AvaliacaoAlbumController {

    private final AvaliacaoAlbumService avaliacaoAlbumService;

    public AvaliacaoAlbumController(AvaliacaoAlbumService avaliacaoAlbumService) {
        this.avaliacaoAlbumService = avaliacaoAlbumService;
    }

    @GetMapping("/albuns/{albumId}/avaliacoes")
    public List<AvaliacaoAlbumResponse> listarPorAlbum(@PathVariable Long albumId) {
        return avaliacaoAlbumService.listarPorAlbum(albumId);
    }

    @PostMapping("/albuns/{albumId}/avaliacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoAlbumResponse criar(@PathVariable Long albumId, @RequestParam Long usuarioId, @Valid @RequestBody AvaliacaoAlbumRequest request) {
        return avaliacaoAlbumService.criar(albumId, usuarioId, request);
    }

    @PutMapping("/avaliacoes/{avaliacaoId}")
    public AvaliacaoAlbumResponse editar(@PathVariable Long avaliacaoId, @Valid @RequestBody AvaliacaoAlbumRequest request) {
        return avaliacaoAlbumService.editar(avaliacaoId, request);
    }

    @DeleteMapping("/avaliacoes/{avaliacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long avaliacaoId) {
        avaliacaoAlbumService.excluir(avaliacaoId);
    }
}
