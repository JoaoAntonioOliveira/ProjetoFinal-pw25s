package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Compra;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.CompraProduto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.CompraProdutoRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CompraProdutoService;

@Service
public class CompraProdutoServiceImpl extends CrudServiceImpl<CompraProduto, Long> 
		implements CompraProdutoService{

	@Autowired
	private CompraProdutoRepository compraProdutoRepository;
	
	@Override
	protected JpaRepository<CompraProduto, Long> getRepository() {
		return compraProdutoRepository;
	}

	public List<CompraProduto> findByCompra(Compra c){
        return compraProdutoRepository.findByCompra(c);
    }
}
