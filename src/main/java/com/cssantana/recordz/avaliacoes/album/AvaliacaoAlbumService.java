
package com.cssantana.recordz.avaliacoes.album;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import com.cssantana.recordz.shared.exception.RegraNegocio;
import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoAlbumService {

    private final AvaliacaoAlbumRepository avaliacaoAlbumRepository;
    private final AlbumRepository albumRepository;
    private final UsuarioRepository usuarioRepository;
    private final AvaliacaoAlbumMapper mapper;

    public AvaliacaoAlbumService(
            AvaliacaoAlbumRepository avaliacaoAlbumRepository,
            AlbumRepository albumRepository,
            UsuarioRepository usuarioRepository,
            AvaliacaoAlbumMapper mapper
    ) {
        this.avaliacaoAlbumRepository = avaliacaoAlbumRepository;
        this.albumRepository = albumRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Transactional
    public AvaliacaoAlbumResponse criar(
            Long albumId,
            Long usuarioId,
            AvaliacaoAlbumRequest request
    ) {
        Album album = albumRepository.findById(albumId).orElseThrow(() ->
                        new RecursoNaoEncontrado("Álbum não encontrado.")
                );

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() ->
                        new RecursoNaoEncontrado("Usuário não encontrado.")
                );

        boolean jaAvaliou = avaliacaoAlbumRepository.existsByUsuarioIdAndAlbumId(usuarioId, albumId);

        if (jaAvaliou) {throw new RegraNegocio("Você já avaliou este álbum."); }

        AvaliacaoAlbum avaliacao = mapper.paraEntidade(request, usuario, album);

        AvaliacaoAlbum salva = avaliacaoAlbumRepository.save(avaliacao);

        return mapper.paraResponse(salva);
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoAlbumResponse> listarPorAlbum(Long albumId) {
        if (!albumRepository.existsById(albumId)) {throw new RecursoNaoEncontrado("Álbum não encontrado.");}

        return avaliacaoAlbumRepository
                .findByAlbumId(albumId)
                .stream()
                .map(mapper::paraResponse)
                .toList();
    }

    @Transactional
    public AvaliacaoAlbumResponse editar(
            Long avaliacaoId,
            AvaliacaoAlbumRequest request
    ) {
        AvaliacaoAlbum avaliacao =
                avaliacaoAlbumRepository.findById(avaliacaoId).orElseThrow(() ->
                        new RecursoNaoEncontrado("Avaliação não encontrada."));

        avaliacao.atualizar(
                request.nota(),
                request.comentario()
        );

        AvaliacaoAlbum atualizada = avaliacaoAlbumRepository.save(avaliacao);

        return mapper.paraResponse(atualizada);
    }

    @Transactional
    public void excluir(Long avaliacaoId) {
        AvaliacaoAlbum avaliacao = avaliacaoAlbumRepository.findById(avaliacaoId).orElseThrow(() ->
                                new RecursoNaoEncontrado("Avaliação não encontrada."));

        avaliacaoAlbumRepository.delete(avaliacao);
    }
}
