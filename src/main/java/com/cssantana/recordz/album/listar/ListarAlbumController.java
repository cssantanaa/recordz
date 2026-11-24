package com.cssantana.recordz.album.listar;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/albuns")
public class ListarAlbumController {
    private final ListarAlbumService service;
    public ListarAlbumController(ListarAlbumService service) { this.service = service; }

    @GetMapping
    public List<AlbumResponse> listar() { return service.executar(); }
}
