package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.model.Categoria;
// @Repository: indica que a Interface é do tipo repositório, ou seja, é responsável pela comunicação com 
// o Banco de dados através dos métodos padrão e das Method Queries
public interface CategoriaRepository extends JpaRepository<Categoria, Long> { // Long representa a Chave Primária PK, que é o atributo que recebeu a anotação @Id na nossa Classe Categoria 

	public List <Categoria> findAllByNomeContainingIgnoreCase(String nome);

}
