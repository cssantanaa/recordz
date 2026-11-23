package com.cssantana.recordz.spotify;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SpotifyApiClient {
    private final RestClient spotifyClient = RestClient.builder()
            .baseUrl("https://api.spotify.com").build();
    private final SpotifyTokenService tokenService;
    private final SpotifyProperties properties;

    public SpotifyApiClient(SpotifyTokenService tokenService, SpotifyProperties properties) {
        this.tokenService = tokenService;
        this.properties = properties;
    }

    public List<SpotifyAlbumData> buscarAlbuns(String consulta) {
        JsonNode resposta = spotifyClient.get()
                .uri(uri -> uri.path("/v1/search")
                        .queryParam("q", consulta)
                        .queryParam("type", "album")
                        .queryParam("limit", 10)
                        .queryParam("market", properties.getMarket())
                        .build())
                .headers(h -> h.setBearerAuth(tokenService.obterToken()))
                .retrieve().body(JsonNode.class);

        JsonNode itens = resposta == null ? null : resposta.path("albums").path("items");
        if (itens == null || !itens.isArray()) return List.of();
        List<SpotifyAlbumData> resultado = new ArrayList<>();
        itens.forEach(item -> resultado.add(mapearAlbum(item)));
        return resultado;
    }

    public SpotifyAlbumData buscarAlbumPorId(String spotifyId) {
        JsonNode resposta = spotifyClient.get()
                .uri(uri -> uri.path("/v1/albums/{id}")
                        .queryParam("market", properties.getMarket())
                        .build(spotifyId))
                .headers(h -> h.setBearerAuth(tokenService.obterToken()))
                .retrieve().body(JsonNode.class);
        if (resposta == null || resposta.path("id").isMissingNode()) {
            throw new IllegalStateException("Álbum não encontrado no Spotify.");
        }
        return mapearAlbum(resposta);
    }

    public List<SpotifyTrackData> buscarFaixas(String spotifyAlbumId) {
        List<SpotifyTrackData> faixas = new ArrayList<>();
        int offset = 0;
        int total;
        do {
            final int pagina = offset;
            JsonNode resposta = spotifyClient.get()
                    .uri(uri -> uri.path("/v1/albums/{id}/tracks")
                            .queryParam("limit", 50)
                            .queryParam("offset", pagina)
                            .queryParam("market", properties.getMarket())
                            .build(spotifyAlbumId))
                    .headers(h -> h.setBearerAuth(tokenService.obterToken()))
                    .retrieve().body(JsonNode.class);
            JsonNode itens = resposta == null ? null : resposta.path("items");
            if (itens == null || !itens.isArray()) break;
            for (JsonNode item : itens) {
                faixas.add(new SpotifyTrackData(
                        item.path("id").asText(),
                        item.path("name").asText(),
                        item.path("track_number").asInt(),
                        item.path("duration_ms").asInt(),
                        item.path("explicit").asBoolean(false)
                ));
            }
            total = resposta.path("total").asInt(faixas.size());
            offset += itens.size();
        } while (offset < total && offset > 0);
        return faixas;
    }

    private SpotifyAlbumData mapearAlbum(JsonNode item) {
        String artista = StreamSupport.stream(item.path("artists").spliterator(), false)
                .map(a -> a.path("name").asText())
                .collect(Collectors.joining(", "));
        String imagem = null;
        JsonNode imagens = item.path("images");
        if (imagens.isArray() && !imagens.isEmpty()) imagem = imagens.get(0).path("url").asText(null);
        return new SpotifyAlbumData(
                item.path("id").asText(), item.path("name").asText(), artista,
                imagem, item.path("release_date").asText(null),
                item.path("total_tracks").asInt(0), item.path("album_type").asText("album"),
                item.path("external_urls").path("spotify").asText(null)
        );
    }
}
