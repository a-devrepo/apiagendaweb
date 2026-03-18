package br.com.nca.apiagendaweb.controllers;

import br.com.nca.apiagendaweb.dtos.TarefaRequest;
import br.com.nca.apiagendaweb.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefasController {

    private TarefaService tarefaService;

    public TarefasController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody TarefaRequest request){
        var response = tarefaService.cadastrar(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> put(){
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(){
        return ResponseEntity.ok().build();
    }
}
