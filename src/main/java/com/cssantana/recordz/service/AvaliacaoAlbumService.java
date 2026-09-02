package com.cssantana.recordz.service;

import com.cssantana.recordz.dto.avaliacao.AvaliacaoAlbumDto;
import com.cssantana.recordz.dto.avaliacao.AvaliacaoDto;
import com.cssantana.recordz.exception.RecursoNaoEncontrado;
import com.cssantana.recordz.exception.RegraNegocio;
import com.cssantana.recordz.mapper.AvaliacaoAlbumMapper;
import com.cssantana.recordz.model.Album;
import com.cssantana.recordz.model.AvaliacaoAlbum;
import com.cssantana.recordz.model.Usuario;
import com.cssantana.recordz.repository.AlbumRepository;
import com.cssantana.recordz.repository.AvaliacaoAlbumRepository;
import com.cssantana.recordz.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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

    public AvaliacaoDto criar(Long albumId, Long usuarioId, AvaliacaoAlbumDto dto) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RecursoNaoEncontrado("Álbum não encontrado."));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado."));

        boolean jaAvaliou =
                avaliacaoAlbumRepository.existsByUsuarioIdAndAlbumId(usuarioId, albumId);

        if (jaAvaliou) {
            throw new RegraNegocio("Você já avaliou este álbum.");
        }

        AvaliacaoAlbum avaliacao = mapper.paraEntidade(dto, usuario, album);

        AvaliacaoAlbum salva = avaliacaoAlbumRepository.save(avaliacao);

        return mapper.paraDTO(salva);
    }

    public List<AvaliacaoDto> listarPorAlbum(Long albumId) {

        if (!albumRepository.existsById(albumId)) {
            throw new RecursoNaoEncontrado("Álbum não encontrado.");
        }

        return avaliacaoAlbumRepository
                .findByAlbumId(albumId)
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    public AvaliacaoDto editar(Long avaliacaoId, AvaliacaoAlbumDto dto) {

        AvaliacaoAlbum avaliacao = avaliacaoAlbumRepository.findById(avaliacaoId)
                        .orElseThrow(() -> new RecursoNaoEncontrado("Avaliação não encontrada."));

        avaliacao.atualizar(dto.getNota(), dto.getComentario());

        AvaliacaoAlbum atualizada = avaliacaoAlbumRepository.save(avaliacao);

        return mapper.paraDTO(atualizada);
    }

    public void excluir(Long avaliacaoId) {

        AvaliacaoAlbum avaliacao = avaliacaoAlbumRepository.findById(avaliacaoId)
                        .orElseThrow(() ->
                                new RecursoNaoEncontrado("Avaliação não encontrada.")
                        );

        avaliacaoAlbumRepository.delete(avaliacao);
    }
}