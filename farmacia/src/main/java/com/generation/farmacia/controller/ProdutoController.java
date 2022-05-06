package com.generation.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController // anotação usada para mostrar para o Spring que essa classe é um controller
@RequestMapping("/produto") 
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired //inicialização do objeto
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping 
	public ResponseEntity<List<Produto>> getAll(){ 
		return ResponseEntity.ok(produtoRepository.findAll()); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)  
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping // post = salvar dados
	public ResponseEntity <Produto> postProduto(@Valid @RequestBody Produto produto){ //@Valid  indica que os dados recebidos devem ser validados.
		return categoriaRepository.findById(produto.getCategoria().getId())           //@RequestBody  anotação usada para dizer para o Spring que os dados devem vir no corpo de uma solicitação web
					.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
					.orElse(ResponseEntity.badRequest().build());
		}
		
	@PutMapping // put = atualizar dados
	public ResponseEntity <Produto> putProduto(@Valid @RequestBody Produto produto){ //@Valid  indica que os dados recebidos devem ser validados.
			
		if (produtoRepository.existsById(produto.getId())){
			return categoriaRepository.findById(produto.getCategoria().getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
					.orElse(ResponseEntity.badRequest().build());
		}		
		return ResponseEntity.notFound().build();
		}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable Long id) {
		produtoRepository.deleteById(id);
	}
}