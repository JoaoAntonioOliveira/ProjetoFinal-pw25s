package br.edu.utfpr.pb.ecommerce.Ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Pedido implements Serializable{
	private static final long serialVersionUID = 5245638191697788281L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Preencha o campo data da compra!")
	@Column(name = "data_compra", nullable = true)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPedido;
	
	@Column(name = "total", nullable = false)
	private BigDecimal total;
	
	@Column(name = "frete", nullable = false)
	private BigDecimal frete;
	
	@Column(name = "formaPag", nullable = false)
	private String formaPag;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<PedidoProduto> pedidoProduto;

    public List<PedidoProduto> getPedidoProduto(){
        return pedidoProduto;
    }
}
