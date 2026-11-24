package com.cssantana.recordz.usuario.cadastrar;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarUsuarioRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100,
                message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "O nome de usuário é obrigatório")
        @Size(min = 3, max = 50, message = "O nome de usuário deve ter entre 3 e 50 caracteres")
        String nomeUsuario,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        @Size(max = 150)
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        String senha,

        @Size(max = 500)
        String biografia,

        @Size(max = 500)
        String fotoPerfil
) {}