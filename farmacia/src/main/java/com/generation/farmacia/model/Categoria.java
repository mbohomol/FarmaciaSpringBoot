package com.generation.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // gera uma tabela no Banco de Dados
@Table(name = "tb_categoria") // cria o nome da tabela do banco de dados
public class Categoria {

	@Id //@Id indica que o atributo é a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY)// @GeneratedValue indica que a chave primária será gerada automaticamente pelo Banco de Dados
	private Long id;        						  // strategy indica como a Chave Primária será gerada //  GenerationType.IDENTITY = auto_increment
	
	@NotBlank(message = "O nome é obrigaório!") // @NotBlank indica que um atributo não pode ser nulo e não pode ser deixado em branco // a mensagem será exibida se o atributo for nulo ou se for deixado em branco
	@Size(min= 1) // @Size tem a função de definir o tamanho minimo e máximo de caracteres de um atributo String
	private String name;
	
	@NotBlank(message = "A descrição é obrigaória!")
	@Size(min= 1, max= 1000)
	private String descricao;
	
	@OneToMany(mappedBy= "categoria", cascade = CascadeType.REMOVE)// indica  que a Classe Categoria terá um relacionamento do tipo One To Many (Um para Muitos) com a Classe Produto 
	//mappedBy = "categoria": indica qual Objeto será utilizado como "chave estrangeira" no relacionamento
	//cascade = CascadeType.REMOVE = exemplo: se apagar uma Categoria, todas os produtos associadas a esta Categoria serão apagados também
	   @JsonIgnoreProperties("categoria") // @JsonIgnoreProperties evita loop
	   private List<Produto> produto; // listará todos os Produtos associados com as respectivas Categorias

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
}
