package io.github.Alexandregabriell.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Representa a requisição na geração do token

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO {
    private String login;
    private String senha;
}
