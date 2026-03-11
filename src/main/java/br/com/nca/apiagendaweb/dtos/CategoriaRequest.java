package br.com.nca.apiagendaweb.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequest {

    @Size(min = 6, message = "Por favor, informe no mínimo 6 caracteres.")
    @NotBlank(message = "Por favor, informe o nome da categoria.")
    private String nome;
}
