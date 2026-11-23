package com.cssantana.recordz.album.excluir;

import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExcluirAlbumService {
    private final AlbumRepository repository;
    public ExcluirAlbumService(AlbumRepository repository) { this.repository = repository; }

    @Transactional
    public void executar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontrado("Álbum não encontrado.");
        }
        repository.deleteById(id);
    }
}
