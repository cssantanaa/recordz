package com.cssantana.recordz.album.excluir;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albuns")
public class ExcluirAlbumController {
    private final ExcluirAlbumService service;
    public ExcluirAlbumController(ExcluirAlbumService service) { this.service = service; }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.executar(id); }
}
