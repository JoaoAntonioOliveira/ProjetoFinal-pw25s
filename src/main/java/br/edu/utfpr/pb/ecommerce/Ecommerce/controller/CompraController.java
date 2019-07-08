package br.edu.utfpr.pb.ecommerce.Ecommerce.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Compra;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.CompraProduto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CompraProdutoService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CompraService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CrudService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.FornecedorService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.ProdutoService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.UsuarioService;

@Controller
@RequestMapping("compra")
public class CompraController extends CrudController<Compra, Long>{

	@Autowired
	private CompraService compraService;
	
	@Autowired
	private CompraProdutoService compraProdutoService;
	
	@Autowired
    private ProdutoService produtoService;

	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
    private UsuarioService usuarioService;

	
	@Override
	protected CrudService<Compra, Long> getService() {
		// TODO Auto-generated method stub
		return compraService;
	}

	@Override
	protected String getURL() {
		// TODO Auto-generated method stub
		return "compra";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Compra compra) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		modelAndView.addObject("fornecedores", fornecedorService.findAll());
        modelAndView.addObject("produtos", produtoService.findAll());
        modelAndView.addObject("usuarios", usuarioService.findAll());
		if(compra != null) {
			modelAndView.addObject(compra);
		}else {
			modelAndView.addObject(new Compra());
		}
		return modelAndView;
	}

	@Override
	@GetMapping("{id}")
	protected ModelAndView form(Long id) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		
		modelAndView.addObject(this.getService().findOne(id));
		return modelAndView;
	}
	
	@Override
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Compra> list = this.getService().findAll(
				PageRequest.of(currentPage -1, pageSize));
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);

		modelAndView.addObject("compras", compraService.findAll());
        modelAndView.addObject("fornecedores", fornecedorService.findAll());
        modelAndView.addObject("produtos", produtoService.findAll());
        modelAndView.addObject("usuarios", usuarioService.findAll());
		
		if(list.getTotalPages()>0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
	}
	
	@PostMapping("json")
    public ResponseEntity<?> saveJson(@RequestBody @Valid Compra entity, BindingResult result, Model model,
                                      RedirectAttributes attributes) {
		
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        entity.setFornecedor(fornecedorService.findOne(entity.getFornecedor().getId()));
        entity.setUsuario(usuarioService.findOne(entity.getUsuario().getId()));

        getService().save(entity);

        for (CompraProduto cp : entity.getCompraProduto()) {
            cp.setProduto(produtoService.findOne(cp.getProduto().getId()));
            BigDecimal total = cp.getQuantidade().multiply(cp.getProduto().getValor());
            cp.setTotal(total);
            cp.setCompra(entity);
            
            compraProdutoService.save(cp);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

	
}
