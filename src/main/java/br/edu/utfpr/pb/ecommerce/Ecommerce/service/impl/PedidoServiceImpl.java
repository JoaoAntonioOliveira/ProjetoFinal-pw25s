package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Pedido;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.PedidoRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.PedidoService;

@Service
public class PedidoServiceImpl extends CrudServiceImpl<Pedido, Long> 
		implements PedidoService{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	protected JpaRepository<Pedido, Long> getRepository() {
		return pedidoRepository;
	}

}
