package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Marca;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.MarcaRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.MarcaService;

@Service
public class MarcaServiceImpl extends CrudServiceImpl<Marca, Integer> 
		implements MarcaService{

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	protected JpaRepository<Marca, Integer> getRepository() {
		return marcaRepository;
	}

}
