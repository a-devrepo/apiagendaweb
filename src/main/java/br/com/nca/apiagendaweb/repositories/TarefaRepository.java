package br.com.nca.apiagendaweb.repositories;

import br.com.nca.apiagendaweb.entities.Categoria;
import br.com.nca.apiagendaweb.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
}
