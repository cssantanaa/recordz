package com.cssantana.recordz.avaliacoes.album.excluir;

import com.cssantana.recordz.avaliacoes.album.shared.repository.AvaliacaoAlbumRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExcluirAvaliacaoAlbumService {

    private final AvaliacaoAlbumRepository repository;

    public ExcluirAvaliacaoAlbumService(AvaliacaoAlbumRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void executar(Long avaliacaoId) {
        var avaliacao = repository.findById(avaliacaoId).orElseThrow(() ->
                new RecursoNaoEncontrado("Avaliação não encontrada."));

        repository.delete(avaliacao);
    }
}