package com.cssantana.recordz.diario.listar;

import com.cssantana.recordz.diario.DiarioRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import com.cssantana.recordz.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListarDiarioService {

    private final DiarioRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final DiarioResponseMapper mapper;

    public ListarDiarioService(
            DiarioRepository repository,
            UsuarioRepository usuarioRepository,
            DiarioResponseMapper mapper
    ) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<DiarioResponse> executar(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RecursoNaoEncontrado("Usuário não encontrado.");
        }

        return repository.findByUsuarioIdOrderByDataEscutaDesc(usuarioId)
                .stream()
                .map(mapper::paraResponse)
                .toList();
    }
}
