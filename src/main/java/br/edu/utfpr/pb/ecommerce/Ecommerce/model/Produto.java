package br.edu.utfpr.pb.ecommerce.Ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Produto implements Serializable{
	private static final long serialVersionUID = -7543363605557353088L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Preencha o campo nome!")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@NotEmpty(message = "Preencha o campo resumo!")
	@Column(name = "descricao", length = 1024, nullable = false)
	private String descricao;
	
	@NotNull(message = "Preencha o campo nota!")
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	@NotNull(message = "Preencha o campo categoria!")
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;
	
	@NotNull(message = "Preencha o campo marca!")
	@ManyToOne
	@JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;
	
	@Column(name = "imagem", length = 100, nullable = true)
	private String imagem;
	
}







