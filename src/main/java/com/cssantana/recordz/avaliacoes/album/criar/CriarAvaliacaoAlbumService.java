package com.cssantana.recordz.avaliacoes.album.criar;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.avaliacoes.album.shared.repository.AvaliacaoAlbumRepository;
import com.cssantana.recordz.avaliacoes.album.shared.mapper.AvaliacaoAlbumResponseMapper;
import com.cssantana.recordz.avaliacoes.album.dominio.AvaliacaoAlbum;
import com.cssantana.recordz.avaliacoes.album.listar.AvaliacaoAlbumResponse;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import com.cssantana.recordz.shared.exception.RegraNegocio;
import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarAvaliacaoAlbumService {

    private final AvaliacaoAlbumRepository repository;
    private final AlbumRepository albumRepository;
    private final UsuarioRepository usuarioRepository;
    private final CriarAvaliacaoAlbumMapper mapper;
    private final AvaliacaoAlbumResponseMapper responseMapper;

    public CriarAvaliacaoAlbumService(
            AvaliacaoAlbumRepository repository,
            AlbumRepository albumRepository,
            UsuarioRepository usuarioRepository,
            CriarAvaliacaoAlbumMapper mapper,
            AvaliacaoAlbumResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.albumRepository = albumRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.responseMapper = responseMapper;
    }

    @Transactional
    public AvaliacaoAlbumResponse executar(Long albumId, Long usuarioId, CriarAvaliacaoAlbumRequest request) {
        Album album = albumRepository.findById(albumId).orElseThrow(() ->
                new RecursoNaoEncontrado("Álbum não encontrado."));

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() ->
                new RecursoNaoEncontrado("Usuário não encontrado."));

        if (repository.existsByUsuarioIdAndAlbumId(usuarioId, albumId)) {
            throw new RegraNegocio("Você já avaliou este álbum.");
        }

        AvaliacaoAlbum avaliacao = mapper.paraEntidade(request, usuario, album);

        AvaliacaoAlbum salva = repository.save(avaliacao);

        return responseMapper.paraResponse(salva);
    }
}