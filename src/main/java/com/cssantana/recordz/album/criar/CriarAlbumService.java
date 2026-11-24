package com.cssantana.recordz.album.criar;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.album.AlbumRepository;
import com.cssantana.recordz.album.listar.AlbumResponse;
import com.cssantana.recordz.album.listar.AlbumResponseMapper;
import com.cssantana.recordz.shared.exception.RegraNegocio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarAlbumService {
    private final AlbumRepository repository;
    private final CriarAlbumMapper mapper;
    private final AlbumResponseMapper responseMapper;

    public CriarAlbumService(AlbumRepository repository, CriarAlbumMapper mapper,
                             AlbumResponseMapper responseMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.responseMapper = responseMapper;
    }

    @Transactional
    public AlbumResponse executar(CriarAlbumRequest request) {
        if (repository.existsByIdSpotify(request.idSpotify())) {
            throw new RegraNegocio("Este álbum já está cadastrado no Recordz.");
        }
        Album salvo = repository.save(mapper.paraEntidade(request));
        return responseMapper.paraResponse(salvo);
    }
}
