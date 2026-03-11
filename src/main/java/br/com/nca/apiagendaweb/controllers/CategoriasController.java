package br.com.nca.apiagendaweb.controllers;

import br.com.nca.apiagendaweb.dtos.CategoriaRequest;
import br.com.nca.apiagendaweb.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasController {

    private CategoriaService categoriaService;

    public CategoriasController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid CategoriaRequest request) {
        var response = categoriaService.cadastrar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable UUID id, @Valid @RequestBody CategoriaRequest request) {
        var response = categoriaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> get() {
        var response = categoriaService.consultar();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
