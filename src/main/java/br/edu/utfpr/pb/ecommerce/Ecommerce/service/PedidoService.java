package br.edu.utfpr.pb.ecommerce.Ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Pedido;

public interface PedidoService extends CrudService<Pedido, Long> {

	Page<Pedido> findAllByUsuarioId(Long id, Pageable pg);
}
