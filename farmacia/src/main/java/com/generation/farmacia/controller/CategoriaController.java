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

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

@RestController //diz que é uma classe controladora Rest
@RequestMapping("/categoria") //  indica que a URL da API desse controller começa com /categoria, ou seja: http://localhost:8080/categoria
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository; // acessa todos os métodos da Interface, ou seja acessa todas categorias
	
	@GetMapping // lista todas as categorias
	public ResponseEntity<List<Categoria>> getAll(){ 
		return ResponseEntity.ok(categoriaRepository.findAll()); // findAll()= seleciona todas as categorias do repository
	}
	
	@GetMapping("/{id}") // lista apenas a categoria buscando pelo seu ID 
	public ResponseEntity<Categoria> getById(@PathVariable Long id){ //@PathVariable Long id = insere o id = ex: http://localhost:8080/categoria/1
		return categoriaRepository.findById(id) 
					.map(resposta -> ResponseEntity.ok(resposta)) //se a categoria existir, a função map retorna o status OK => 200 
					.orElse(ResponseEntity.notFound().build()); //se a categoria não for encontrada, retorna o status Not Found => 404
	}
	
	@GetMapping("/nome/{nome}") //busca pelo nome de uma categoria
	public ResponseEntity <List<Categoria>>getByNome(@PathVariable String nome){ // getbyName = busca pelo nome // @PathVariable adiciona o nome na url, ex: http://localhost:8080/categoria/nome
		return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome)); //findAll retorna uma lista de dados da tabela 
		}
	
	@PostMapping // salva uma categoria
	public ResponseEntity <Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));		
	}
	
	@PutMapping // atualiza uma categoria
	public ResponseEntity <Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
		
		return categoriaRepository.findById(categoria.getId()) // findById = para obter um contato específico é utilizar o método  = select * from contacts where id = X
				.map(resposta -> ResponseEntity.ok().body(categoriaRepository.save(categoria))) // se a postagem existir, a função  map  executa o método save(categoria) e retorna o status OK = 200
				.orElse(ResponseEntity.notFound().build()); //se a postagem não for encontrada pelo método findById(postagem.getId()),  retorná o status Not Found = 404
	}
	
	@DeleteMapping("/{id}") // deleta uma categoria
	public void deleteCategoria(@PathVariable Long id) { // deleteCategoria(@PathVariable Long id) é do tipo ResponseEntity pq ele responde uma Requisição  
		                                                 // se a categoria for encontrada e excluída da tabela aparecerá = Request Response Status 204 => NO_CONTENT 
		                                                // caso não seja encontrada, a resposta será =  Not Found => 404
		categoriaRepository.deleteById(id);
		
	}
}
