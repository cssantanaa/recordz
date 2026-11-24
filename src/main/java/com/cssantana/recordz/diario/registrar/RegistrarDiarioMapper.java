package com.cssantana.recordz.diario.registrar;

import com.cssantana.recordz.album.Album;
import com.cssantana.recordz.diario.Diario;
import com.cssantana.recordz.musica.Musica;
import com.cssantana.recordz.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class RegistrarDiarioMapper {

    public Diario paraEntidade(
            RegistrarDiarioRequest request,
            Usuario usuario,
            Album album,
            Musica musica
    ) {
        return Diario.criar(request.dataEscuta(), usuario, album, musica);
    }
}
