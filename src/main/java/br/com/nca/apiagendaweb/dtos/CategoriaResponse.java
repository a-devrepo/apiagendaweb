package br.com.nca.apiagendaweb.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoriaResponse {

    private UUID id;
    private String nome;
}
