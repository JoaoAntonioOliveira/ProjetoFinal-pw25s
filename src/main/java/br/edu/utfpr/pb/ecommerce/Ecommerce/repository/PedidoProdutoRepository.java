package br.edu.utfpr.pb.ecommerce.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Pedido;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.PedidoProduto;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long>{
	List<PedidoProduto> findByPedido(Pedido p);
}
