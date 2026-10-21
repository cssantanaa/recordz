package com.cssantana.recordz.avaliacoes.album.listar;

import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.avaliacoes.album.shared.repository.AvaliacaoAlbumRepository;
import com.cssantana.recordz.avaliacoes.album.shared.mapper.AvaliacaoAlbumResponseMapper;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListarAvaliacoesAlbumService {

    private final AvaliacaoAlbumRepository repository;
    private final AlbumRepository albumRepository;
    private final AvaliacaoAlbumResponseMapper responseMapper;

    public ListarAvaliacoesAlbumService(
            AvaliacaoAlbumRepository repository,
            AlbumRepository albumRepository,
            AvaliacaoAlbumResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.albumRepository = albumRepository;
        this.responseMapper = responseMapper;
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoAlbumResponse> executar(Long albumId) {
        if (!albumRepository.existsById(albumId)) {throw new RecursoNaoEncontrado("Álbum não encontrado.");}

        return repository.findByAlbumId(albumId)
                .stream()
                .map(responseMapper::paraResponse)
                .toList();
    }
}