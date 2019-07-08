package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Fornecedor;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.FornecedorRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.FornecedorService;

@Service
public class FornecedorServiceImpl extends CrudServiceImpl<Fornecedor, Integer> 
		implements FornecedorService{

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	protected JpaRepository<Fornecedor, Integer> getRepository() {
		return fornecedorRepository;
	}

}
