package com.produtos.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoRepository produtoRepository;

	// Busca uma lista com todos os produtos
	@GetMapping
	public List<Produto> listaProdutos() {
		return produtoRepository.findAll();
	}
	
	// Busca produto pelo ID
	@GetMapping("/{id}")
	public Produto listaProdutoUnico(@PathVariable(value="id") long id) {
		return produtoRepository.findById(id);
	}

	// Salva um produto novo
	@PostMapping
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	// Deleta produto pelo ID
	@DeleteMapping("/{id}")
	public void deletaProduto(@PathVariable(value="id") long id) {
		produtoRepository.deleteById(id);
	}
	
	// Atualiza os dados do produto cadastrado
	@PutMapping
	public Produto atualizaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
}
