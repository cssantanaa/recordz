package com.cssantana.recordz.controller;

import com.cssantana.recordz.dto.avaliacao.AvaliacaoAlbumDto;
import com.cssantana.recordz.dto.avaliacao.AvaliacaoDto;
import com.cssantana.recordz.service.AvaliacaoAlbumService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AvaliacaoDto>> listarPorAlbum(@PathVariable Long albumId) {

        List<AvaliacaoDto> avaliacoes = avaliacaoAlbumService.listarPorAlbum(albumId);

        return ResponseEntity.ok(avaliacoes);
    }


    @PostMapping("/albuns/{albumId}/avaliacoes")
    public ResponseEntity<AvaliacaoDto> criar(@PathVariable Long albumId, @RequestParam Long usuarioId, @Valid @RequestBody AvaliacaoAlbumDto dto) {

        AvaliacaoDto avaliacao = avaliacaoAlbumService.criar(albumId, usuarioId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    }

    @PutMapping("/avaliacoes/{avaliacaoId}")
    public ResponseEntity<AvaliacaoDto> editar(@PathVariable Long avaliacaoId, @Valid @RequestBody AvaliacaoAlbumDto dto) {

        AvaliacaoDto avaliacao = avaliacaoAlbumService.editar(avaliacaoId, dto);

        return ResponseEntity.ok(avaliacao);
    }


    @DeleteMapping("/avaliacoes/{avaliacaoId}")
    public ResponseEntity<Void> excluir(@PathVariable Long avaliacaoId) {

        avaliacaoAlbumService.excluir(avaliacaoId);

        return ResponseEntity.noContent().build();
    }
}