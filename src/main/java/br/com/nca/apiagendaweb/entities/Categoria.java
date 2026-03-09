package br.com.nca.apiagendaweb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categorias")
@Setter
@Getter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "usuarioid", length = 150, nullable = false)
    private UUID usuarioId;

    @OneToMany(mappedBy = "categoria")
    private List<Tarefa> tarefas;

    public Categoria(){}

    public Categoria(UUID id, String nome, UUID usuarioId) {
        this.id = id;
        this.nome = nome;
        this.usuarioId = usuarioId;
    }
}