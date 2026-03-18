package br.com.nca.apiagendaweb.services;


import br.com.nca.apiagendaweb.dtos.CategoriaResponse;
import br.com.nca.apiagendaweb.dtos.TarefaRequest;
import br.com.nca.apiagendaweb.dtos.TarefaResponse;
import br.com.nca.apiagendaweb.entities.Tarefa;
import br.com.nca.apiagendaweb.enums.Prioridade;
import br.com.nca.apiagendaweb.repositories.CategoriaRepository;
import br.com.nca.apiagendaweb.repositories.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;
    private CategoriaRepository categoriaRepository;

    public TarefaService(TarefaRepository tarefaRepository, CategoriaRepository categoriaRepository) {
        this.tarefaRepository = tarefaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public TarefaResponse cadastrar(TarefaRequest request) {

        var categoria = categoriaRepository.findById(request.getCategoriaId());

        if(categoria.isEmpty()){
            throw new IllegalArgumentException("A categoria informada não existe, verifique o ID");
        }

        var tarefa = toTarefa(request);

        tarefa.setCategoria(categoria.get());

        tarefaRepository.save(tarefa);

        return toTarefaResponse(tarefa);
    }


    public List<TarefaResponse> consultar() {

        var tarefas = tarefaRepository.findAll();

        return tarefas.stream().map(tarefa -> toTarefaResponse(tarefa)).toList();
    }

    private Tarefa toTarefa(TarefaRequest request) {
        var tarefa = new Tarefa();

        tarefa.setNome(request.getNome());

        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        tarefa.setData(LocalDate.parse(request.getData(),dateFormatter));

        var horaForatter = DateTimeFormatter.ofPattern("HH:mm");
        tarefa.setHora(LocalTime.parse(request.getHora(),horaForatter));

        tarefa.setPrioridade(Prioridade.valueOf(request.getPrioridade()));

        tarefa.setUsuarioId(UUID.randomUUID());

        return tarefa;
    }

    private TarefaResponse toTarefaResponse(Tarefa tarefa) {

        var response = new TarefaResponse();

        response.setId(tarefa.getId());
        response.setNome(tarefa.getNome());
        response.setData(tarefa.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        response.setHora(tarefa.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
        response.setPrioridade(tarefa.getPrioridade().toString());

        response.setCategoria(new CategoriaResponse());
        response.getCategoria().setId(tarefa.getCategoria().getId());
        response.getCategoria().setNome(tarefa.getCategoria().getNome());

        return response;
    }
}
