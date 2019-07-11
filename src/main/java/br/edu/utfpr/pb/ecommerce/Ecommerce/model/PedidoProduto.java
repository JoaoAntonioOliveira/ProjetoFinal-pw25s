package br.edu.utfpr.pb.ecommerce.Ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "pedidoProduto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class PedidoProduto implements Serializable{
	private static final long serialVersionUID = -5722276395420122177L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Preencha o campo quantidade!")
	@Column(name = "quantidade", nullable = false)
	private BigDecimal quantidade;
	
	@Column(name = "total", nullable = false)
	private BigDecimal total;
	
	@NotNull(message = "Preencha o campo produto!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Pedido pedido;


}
