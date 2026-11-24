package com.cssantana.recordz.spotify;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Service
public class SpotifyTokenService {
    private final SpotifyProperties properties;
    private final RestClient accountsClient = RestClient.builder()
            .baseUrl("https://accounts.spotify.com").build();

    private String accessToken;
    private Instant expiresAt = Instant.EPOCH;

    public SpotifyTokenService(SpotifyProperties properties) {
        this.properties = properties;
    }

    public synchronized String obterToken() {
        if (accessToken != null && Instant.now().isBefore(expiresAt.minusSeconds(60))) {
            return accessToken;
        }
        if (properties.getClientId() == null || properties.getClientId().isBlank()
                || properties.getClientSecret() == null || properties.getClientSecret().isBlank()) {
            throw new IllegalStateException("Configure SPOTIFY_CLIENT_ID e SPOTIFY_CLIENT_SECRET.");
        }

        String credenciais = properties.getClientId() + ":" + properties.getClientSecret();
        String basic = Base64.getEncoder().encodeToString(credenciais.getBytes(StandardCharsets.UTF_8));
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");

        JsonNode resposta = accountsClient.post()
                .uri("/api/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Basic " + basic)
                .body(form)
                .retrieve()
                .body(JsonNode.class);

        if (resposta == null || resposta.path("access_token").asText().isBlank()) {
            throw new IllegalStateException("O Spotify não retornou um access token válido.");
        }
        this.accessToken = resposta.path("access_token").asText();
        this.expiresAt = Instant.now().plusSeconds(resposta.path("expires_in").asLong(3600));
        return accessToken;
    }
}
