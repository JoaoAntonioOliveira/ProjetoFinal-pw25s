package br.edu.utfpr.pb.ecommerce.Ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Permissao;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.PermissaoRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.PermissaoService;

@Service
public class PermissaoServiceImpl extends CrudServiceImpl<Permissao, Integer> 
	implements PermissaoService{

@Autowired
private PermissaoRepository permissaoRepository;

@Override
protected JpaRepository<Permissao, Integer> getRepository() {
return permissaoRepository;
}

}
