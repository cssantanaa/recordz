package com.cssantana.recordz.album.editar;

import com.cssantana.recordz.album.listar.AlbumResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albuns")
public class EditarAlbumController {
    private final EditarAlbumService service;
    public EditarAlbumController(EditarAlbumService service) { this.service = service; }

    @PutMapping("/{id}")
    public AlbumResponse editar(@PathVariable Long id,
                                @Valid @RequestBody EditarAlbumRequest request) {
        return service.executar(id, request);
    }
}
