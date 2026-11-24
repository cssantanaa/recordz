package com.cssantana.recordz.album.importar;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.album.TipoAlbum;
import com.cssantana.recordz.album.listar.AlbumResponse;
import com.cssantana.recordz.album.listar.AlbumResponseMapper;
import com.cssantana.recordz.musica.Musica;
import com.cssantana.recordz.musica.MusicaRepository;
import com.cssantana.recordz.spotify.SpotifyAlbumData;
import com.cssantana.recordz.spotify.SpotifyApiClient;
import com.cssantana.recordz.spotify.SpotifyTrackData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class ImportarAlbumSpotifyService {
    private final SpotifyApiClient spotify;
    private final AlbumRepository albumRepository;
    private final MusicaRepository musicaRepository;
    private final AlbumResponseMapper responseMapper;

    public ImportarAlbumSpotifyService(SpotifyApiClient spotify, AlbumRepository albumRepository,
                                       MusicaRepository musicaRepository,
                                       AlbumResponseMapper responseMapper) {
        this.spotify = spotify;
        this.albumRepository = albumRepository;
        this.musicaRepository = musicaRepository;
        this.responseMapper = responseMapper;
    }

    public List<SpotifyAlbumData> pesquisar(String consulta) {
        if (consulta == null || consulta.isBlank()) {
            throw new IllegalArgumentException("Informe o nome do álbum ou artista para pesquisar.");
        }
        return spotify.buscarAlbuns(consulta.trim());
    }

    @Transactional
    public AlbumResponse importar(String spotifyId) {
        var existente = albumRepository.findByIdSpotify(spotifyId);
        if (existente.isPresent()) return responseMapper.paraResponse(existente.get());

        SpotifyAlbumData dados = spotify.buscarAlbumPorId(spotifyId);
        List<SpotifyTrackData> faixas = spotify.buscarFaixas(spotifyId);

        Album album = Album.criar(dados.id(), dados.nome(), dados.artista(),
                dados.imagemUrl(), dados.dataLancamento(), dados.quantidadeFaixas(),
                converterTipo(dados.tipo()));
        Album salvo = albumRepository.save(album);

        List<Musica> musicas = faixas.stream()
                .filter(f -> f.id() != null && !f.id().isBlank())
                .map(f -> Musica.criar(f.id(), f.nome(), f.numeroFaixa(),
                        f.duracaoMs(), f.explicita(), salvo))
                .toList();
        musicaRepository.saveAll(musicas);
        return responseMapper.paraResponse(salvo);
    }

    private TipoAlbum converterTipo(String tipo) {
        return switch (tipo.toLowerCase(Locale.ROOT)) {
            case "single" -> TipoAlbum.SINGLE;
            case "compilation" -> TipoAlbum.COMPILACAO;
            default -> TipoAlbum.ALBUM;
        };
    }
}
