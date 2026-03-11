package br.com.nca.apiagendaweb.services;

import br.com.nca.apiagendaweb.dtos.CategoriaRequest;
import br.com.nca.apiagendaweb.dtos.CategoriaResponse;
import br.com.nca.apiagendaweb.entities.Categoria;
import br.com.nca.apiagendaweb.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponse cadastrar(CategoriaRequest request){
        var categoria = new Categoria();
        categoria.setNome(request.getNome());

        //Provisório
        categoria.setUsuarioId(UUID.randomUUID());

        categoriaRepository.save(categoria);

        return  toCategoriaResponse(categoria);
    }

    public CategoriaResponse atualizar(UUID id, CategoriaRequest request){

        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para atualizar."));

        categoria.setNome(request.getNome());

        categoria = categoriaRepository.save(categoria);

        return toCategoriaResponse(categoria);
    }

    public CategoriaResponse excluir(UUID id){

        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para exclusão."));

        categoriaRepository.delete(categoria);

        return  toCategoriaResponse(categoria);
    }

    public List<CategoriaResponse> consultar() {

        var categorias = categoriaRepository.findAll();

        return categorias
                .stream()
                .map(this::toCategoriaResponse)
                .toList();
    }

    private CategoriaResponse toCategoriaResponse(Categoria categoria) {
        var response = new CategoriaResponse();
        response.setId(categoria.getId());
        response.setNome(categoria.getNome());
        return response;
    }
}
