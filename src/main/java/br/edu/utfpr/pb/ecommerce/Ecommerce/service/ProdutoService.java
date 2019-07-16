package br.edu.utfpr.pb.ecommerce.Ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Produto;

public interface ProdutoService extends CrudService<Produto, Long> {
	Page<Produto> findAllByNomeLike(String c, Pageable pageRequest);

	Page<Produto> findAllByCategoriaId(Integer integer, Pageable pageRequest);
}
