package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Categoria;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.CategoriaRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CategoriaService;

@Service
public class CategoriaServiceImpl extends CrudServiceImpl<Categoria, Integer> 
		implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	protected JpaRepository<Categoria, Integer> getRepository() {
		return categoriaRepository;
	}

}
