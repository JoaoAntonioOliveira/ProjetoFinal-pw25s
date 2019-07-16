package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Produto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.ProdutoRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.ProdutoService;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, Long> 
		implements ProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	protected JpaRepository<Produto, Long> getRepository() {
		return produtoRepository;
	}

	@Override
	public Page<Produto> findAllByNomeLike(String c, Pageable pageRequest) {
		// TODO Auto-generated method stub
		return produtoRepository.findAllByNomeLike(c, pageRequest);
	}
	
	@Override
	public Page<Produto> findAllByCategoriaId(Integer i, Pageable page) {
		return produtoRepository.findAllByCategoriaId(i,page);
	}

}
	