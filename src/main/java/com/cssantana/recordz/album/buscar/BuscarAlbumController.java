package com.cssantana.recordz.album.buscar;

import com.cssantana.recordz.album.listar.AlbumResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albuns")
public class BuscarAlbumController {
    private final BuscarAlbumService service;
    public BuscarAlbumController(BuscarAlbumService service) { this.service = service; }

    @GetMapping("/{id}")
    public AlbumResponse buscar(@PathVariable Long id) { return service.executar(id); }
}
