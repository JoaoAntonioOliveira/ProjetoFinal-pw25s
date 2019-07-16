package br.edu.utfpr.pb.ecommerce.Ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	Page<Pedido> findAllByUsuarioId(Long id, Pageable pg);
}
