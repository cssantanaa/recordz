package com.cssantana.recordz.avaliacoes.album.editar;

import com.cssantana.recordz.avaliacoes.album.shared.repository.AvaliacaoAlbumRepository;
import com.cssantana.recordz.avaliacoes.album.shared.mapper.AvaliacaoAlbumResponseMapper;
import com.cssantana.recordz.avaliacoes.album.dominio.AvaliacaoAlbum;
import com.cssantana.recordz.avaliacoes.album.listar.AvaliacaoAlbumResponse;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditarAvaliacaoAlbumService {

    private final AvaliacaoAlbumRepository repository;
    private final AvaliacaoAlbumResponseMapper responseMapper;

    public EditarAvaliacaoAlbumService(AvaliacaoAlbumRepository repository, AvaliacaoAlbumResponseMapper responseMapper) {
        this.repository = repository;
        this.responseMapper = responseMapper;
    }

    @Transactional
    public AvaliacaoAlbumResponse executar(Long avaliacaoId, EditarAvaliacaoAlbumRequest request) {
        AvaliacaoAlbum avaliacao = repository.findById(avaliacaoId).orElseThrow(() ->
                new RecursoNaoEncontrado("Avaliação não encontrada."));

        avaliacao.atualizar(request.nota(), request.comentario());

        AvaliacaoAlbum atualizada = repository.save(avaliacao);

        return responseMapper.paraResponse(atualizada);
    }
}