package br.edu.utfpr.pb.ecommerce.Ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Page<Produto> findAllByNomeLike(String c, Pageable pageRequest);
	
	Page<Produto> findAllByCategoriaId(Integer i, Pageable page);
}
