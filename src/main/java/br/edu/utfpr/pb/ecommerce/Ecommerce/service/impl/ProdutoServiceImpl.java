package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Produto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.ProdutoRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.ProdutoService;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, Long> 
		implements ProdutoService{

	@Autowired
	private ProdutoRepository serieRepository;
	
	@Override
	protected JpaRepository<Produto, Long> getRepository() {
		return serieRepository;
	}

}
