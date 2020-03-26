package com.produtos.apirest.resources;

import java.math.BigDecimal;
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

import com.produtos.apirest.models.Pedido;
import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.PedidoRepository;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	PedidoRepository pedidoRepository;

	// Busca uma lista com todos os pedidos
	@GetMapping
	public List<Pedido> listaPedidos() {
		return pedidoRepository.findAll();
	}
	
	// Busca o pedido pelo ID
	@GetMapping("/{id}")
	public Pedido listaPedidoUnico(@PathVariable(value="id") long id) {
		
		Pedido pedido = pedidoRepository.findById(id);
		Produto produto = pedido.getProduto();
		
		// Valida o desconto do pedido caso não for serviço
		if(produto.isService() == false){
			BigDecimal quantidadeDoDesconto = produto.getValor().divideAndRemainder(new BigDecimal(pedido.getDesconto()))[0];
		
			produto.setValor(produto.getValor().subtract(quantidadeDoDesconto));
			pedido.setProduto(produto);
		}
		
		return pedido;
	}

	// Salva um pedido novo
	@PostMapping
	public Pedido salvaPedido(@RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	// Deleta pedido pelo ID
	@DeleteMapping("/{id}")
	public void deletaPedido(@PathVariable(value="id") long id) {
		pedidoRepository.deleteById(id);
	}
	
	// Atualiza os dados do pedido cadastrado
	@PutMapping
	public Pedido atualizaPedido(@RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
}
