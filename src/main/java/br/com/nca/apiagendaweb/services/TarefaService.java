package br.com.nca.apiagendaweb.services;


import br.com.nca.apiagendaweb.repositories.TarefaRepository;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


}
