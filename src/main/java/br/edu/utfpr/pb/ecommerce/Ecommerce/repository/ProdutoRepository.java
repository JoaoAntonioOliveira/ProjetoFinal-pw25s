package br.edu.utfpr.pb.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
