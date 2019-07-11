package br.edu.utfpr.pb.ecommerce.Ecommerce.service;

import java.util.List;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Pedido;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.PedidoProduto;

public interface PedidoProdutoService extends CrudService<PedidoProduto, Long> {

	List<PedidoProduto> findByPedido(Pedido p);	
}
