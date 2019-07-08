package br.edu.utfpr.pb.ecommerce.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Compra;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.CompraProduto;

public interface CompraProdutoRepository extends JpaRepository<CompraProduto, Long>{
	List<CompraProduto> findByCompra(Compra c);
}
