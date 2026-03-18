package br.com.nca.apiagendaweb.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TarefaResponse {

    private UUID id;
    private String nome;
    private String data;
    private String hora;
    private String prioridade;
    private CategoriaResponse categoria;
}
