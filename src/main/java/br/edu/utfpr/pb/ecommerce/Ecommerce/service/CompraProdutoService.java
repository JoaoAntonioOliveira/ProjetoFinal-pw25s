package br.edu.utfpr.pb.ecommerce.Ecommerce.service;

import java.util.List;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Compra;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.CompraProduto;

public interface CompraProdutoService extends CrudService<CompraProduto, Long> {

	List<CompraProduto> findByCompra(Compra c);	
}
