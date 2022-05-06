package com.generation.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name= "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "O nome é obrigatório!")
	@Size(min=1)
	private String nome;
	
	@NotBlank(message= "A descrição é obrigatória!")
	@Size(max=1000)
	private String descricao;
	
	@PositiveOrZero(message= "A quantidade é obrigatória!")
    private int quantidade; 
	
	@NotBlank(message= "O laboratório é o!")
	@Size(max=100)
	private String laboratorio;
	
	@Positive(message= "O preço é obrigatório")
	private BigDecimal preco;
	
	@NotBlank(message = "A foto é obrigatória!")
    private String foto;
	
	@ManyToOne // Muitos para 1
    @JsonIgnoreProperties("produto") // serve para não ser um loop infinito
    private Categoria categoria;  // cria o objeto categoria

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
