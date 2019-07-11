package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Pedido;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.PedidoProduto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.PedidoProdutoRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.PedidoProdutoService;

@Service
public class PedidoProdutoServiceImpl extends CrudServiceImpl<PedidoProduto, Long> 
		implements PedidoProdutoService{

	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;
	
	@Override
	protected JpaRepository<PedidoProduto, Long> getRepository() {
		return pedidoProdutoRepository;
	}

	public List<PedidoProduto> findByPedido(Pedido p){
        return pedidoProdutoRepository.findByPedido(p);
    }
}
