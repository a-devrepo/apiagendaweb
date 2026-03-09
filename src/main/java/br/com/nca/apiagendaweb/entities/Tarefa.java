package br.com.nca.apiagendaweb.entities;

import br.com.nca.apiagendaweb.enums.Prioridade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tarefas")
@Setter
@Getter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade", nullable = false)
    private Prioridade prioridade;

    @ManyToOne
    @JoinColumn(name = "categoriaid",nullable = false)
    private Categoria categoria;

    public Tarefa(){}

    public Tarefa(UUID id, String nome, LocalDate data, LocalTime hora, Prioridade prioridade, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.prioridade = prioridade;
        this.categoria = categoria;
    }
}
