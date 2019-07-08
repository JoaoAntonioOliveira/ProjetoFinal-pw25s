package br.edu.utfpr.pb.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer>{

	Permissao findByNome(String string);

	
}
