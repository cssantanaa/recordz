package com.cssantana.recordz.album.buscar;

import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.album.listar.AlbumResponse;
import com.cssantana.recordz.album.listar.AlbumResponseMapper;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscarAlbumService {
    private final AlbumRepository repository;
    private final AlbumResponseMapper mapper;

    public BuscarAlbumService(AlbumRepository repository, AlbumResponseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public AlbumResponse executar(Long id) {
        return repository.findById(id).map(mapper::paraResponse)
                .orElseThrow(() -> new RecursoNaoEncontrado("Álbum não encontrado."));
    }
}
