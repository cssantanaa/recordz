package com.cssantana.recordz.album.importar;

import com.cssantana.recordz.album.listar.AlbumResponse;
import com.cssantana.recordz.spotify.SpotifyAlbumData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/albuns/spotify")
public class ImportarAlbumSpotifyController {
    private final ImportarAlbumSpotifyService service;
    public ImportarAlbumSpotifyController(ImportarAlbumSpotifyService service) {
        this.service = service;
    }

    @GetMapping("/buscar")
    public List<SpotifyAlbumData> pesquisar(@RequestParam("q") String consulta) {
        return service.pesquisar(consulta);
    }

    @PostMapping("/{spotifyId}/importar")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponse importar(@PathVariable String spotifyId) {
        return service.importar(spotifyId);
    }
}
