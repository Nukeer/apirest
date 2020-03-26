package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	Pedido findById(long id);
	
}
