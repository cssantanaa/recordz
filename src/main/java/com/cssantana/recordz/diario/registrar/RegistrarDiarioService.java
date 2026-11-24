package com.cssantana.recordz.diario.registrar;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.diario.Diario;
import com.cssantana.recordz.diario.DiarioRepository;
import com.cssantana.recordz.diario.listar.DiarioResponse;
import com.cssantana.recordz.diario.listar.DiarioResponseMapper;
import com.cssantana.recordz.musica.Musica;
import com.cssantana.recordz.musica.MusicaRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import com.cssantana.recordz.shared.exception.RegraNegocio;
import com.cssantana.recordz.usuario.Usuario;
import com.cssantana.recordz.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrarDiarioService {

    private final DiarioRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final AlbumRepository albumRepository;
    private final MusicaRepository musicaRepository;
    private final RegistrarDiarioMapper mapper;
    private final DiarioResponseMapper responseMapper;

    public RegistrarDiarioService(
            DiarioRepository repository,
            UsuarioRepository usuarioRepository,
            AlbumRepository albumRepository,
            MusicaRepository musicaRepository,
            RegistrarDiarioMapper mapper,
            DiarioResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.albumRepository = albumRepository;
        this.musicaRepository = musicaRepository;
        this.mapper = mapper;
        this.responseMapper = responseMapper;
    }

    @Transactional
    public DiarioResponse executar(RegistrarDiarioRequest request) {
        boolean temAlbum = request.albumId() != null;
        boolean temMusica = request.musicaId() != null;

        if (temAlbum == temMusica) {
            throw new RegraNegocio("Informe um álbum ou uma faixa para registrar no diário, mas não ambos.");
        }

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado."));

        Album album = null;
        Musica musica = null;

        if (temAlbum) {
            album = albumRepository.findById(request.albumId())
                    .orElseThrow(() -> new RecursoNaoEncontrado("Álbum não encontrado."));
        } else {
            musica = musicaRepository.findById(request.musicaId())
                    .orElseThrow(() -> new RecursoNaoEncontrado("Faixa não encontrada."));
        }

        Diario diario = mapper.paraEntidade(request, usuario, album, musica);
        Diario salvo = repository.save(diario);

        return responseMapper.paraResponse(salvo);
    }
}
