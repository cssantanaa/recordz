package com.cssantana.recordz.diario.excluir;

import com.cssantana.recordz.diario.DiarioRepository;
import com.cssantana.recordz.shared.exception.RecursoNaoEncontrado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExcluirDiarioService {

    private final DiarioRepository repository;

    public ExcluirDiarioService(DiarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void executar(Long id) {
        var diario = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Registro do diário não encontrado."));

        repository.delete(diario);
    }
}
