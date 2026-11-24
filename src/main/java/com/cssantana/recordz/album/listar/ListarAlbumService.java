package com.cssantana.recordz.album.listar;

import com.cssantana.recordz.album.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ListarAlbumService {
    private final AlbumRepository repository;
    private final AlbumResponseMapper mapper;

    public ListarAlbumService(AlbumRepository repository, AlbumResponseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<AlbumResponse> executar() {
        return repository.findAll().stream().map(mapper::paraResponse).toList();
    }
}
