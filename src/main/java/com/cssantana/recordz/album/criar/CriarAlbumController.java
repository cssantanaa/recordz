package com.cssantana.recordz.album.criar;

import com.cssantana.recordz.album.listar.AlbumResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albuns")
public class CriarAlbumController {
    private final CriarAlbumService service;

    public CriarAlbumController(CriarAlbumService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponse criar(@Valid @RequestBody CriarAlbumRequest request) {
        return service.executar(request);
    }
}
