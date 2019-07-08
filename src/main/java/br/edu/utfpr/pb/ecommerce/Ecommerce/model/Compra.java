package br.edu.utfpr.pb.ecommerce.Ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Compra implements Serializable{
	private static final long serialVersionUID = 1309263947593674652L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Preencha o campo Fornecedor!")
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
	private Fornecedor fornecedor;
	
	@NotNull(message = "Preencha o campo data da compra!")
	@Column(name = "data_compra", nullable = true)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompra;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
	
	@OneToMany(mappedBy = "compra", fetch = FetchType.LAZY)
	private List<CompraProduto> compraProduto;

    public List<CompraProduto> getCompraProduto(){
        return compraProduto;
    }
}







