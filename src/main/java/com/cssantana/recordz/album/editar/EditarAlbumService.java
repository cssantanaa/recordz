package com.cssantana.recordz.album.editar;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.album.listar.AlbumResponse;
import com.cssantana.recordz.album.listar.AlbumResponseMapper;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditarAlbumService {
    private final AlbumRepository repository;
    private final AlbumResponseMapper mapper;

    public EditarAlbumService(AlbumRepository repository, AlbumResponseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public AlbumResponse executar(Long id, EditarAlbumRequest request) {
        Album album = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Álbum não encontrado."));
        album.atualizarDados(request.nome(), request.artista(), request.imagemUrl(),
                request.dataLancamento(), request.quantidadeFaixas(), request.tipo());
        return mapper.paraResponse(album);
    }
}
