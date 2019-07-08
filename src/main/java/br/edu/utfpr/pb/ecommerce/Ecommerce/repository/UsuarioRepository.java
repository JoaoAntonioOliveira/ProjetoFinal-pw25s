package br.edu.utfpr.pb.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
}
