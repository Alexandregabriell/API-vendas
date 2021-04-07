package io.github.Alexandregabriell.rest.dto;

// Retorno do token autenticado para o usuario

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String usuario;
    private String token;
}
