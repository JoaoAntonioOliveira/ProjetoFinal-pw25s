package br.edu.utfpr.pb.ecommerce.Ecommerce.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Pedido;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.PedidoProduto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Usuario;
import br.edu.utfpr.pb.ecommerce.Ecommerce.repository.UsuarioRepository;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CrudService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.PedidoProdutoService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.PedidoService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.ProdutoService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.UsuarioService;

@Controller
@RequestMapping("pedido")
public class PedidoController extends CrudController<Pedido, Long>{

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoProdutoService pedidoProdutoService;
	
	@Autowired
    private ProdutoService produtoService;
	
	@Autowired
    private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@Override
	protected CrudService<Pedido, Long> getService() {
		// TODO Auto-generated method stub
		return pedidoService;
	}

	@Override
	protected String getURL() {
		// TODO Auto-generated method stub
		return "pedido";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Pedido pedido) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
        modelAndView.addObject("produtos", produtoService.findAll());
        modelAndView.addObject("usuarios", usuarioService.findAll());
		if(pedido != null) {
			modelAndView.addObject(pedido);
		}else {
			modelAndView.addObject(new Pedido());
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
		
		Usuario usuario = usuarioRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Page<Pedido> list = pedidoService.findAllByUsuarioId(usuario.getId(),
				PageRequest.of(currentPage -1, pageSize));
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);

		modelAndView.addObject("pedidos", pedidoService.findAll());
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
	
	@GetMapping("pageProduto")
	public ModelAndView listProduto() {
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/carrinho");
		
		return modelAndView;
	}

	@PostMapping("json")
    public ResponseEntity<?> saveJson(@RequestBody Pedido entity, BindingResult result, Model model,
                                      RedirectAttributes attributes) {
		BigDecimal totalPedido = BigDecimal.ZERO;
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        
        LocalDate data = LocalDate.now();
        entity.setDataPedido(data);
        
        Usuario usuario = usuarioRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        
        entity.setUsuario(usuario);
        
        Pedido p = getService().save(entity);

        for (PedidoProduto pp : entity.getPedidoProduto()) {
            pp.setProduto(produtoService.findOne(pp.getProduto().getId()));
            BigDecimal total = pp.getQuantidade().multiply(pp.getProduto().getValor());
            pp.setTotal(total);
            pp.setPedido(entity);
            
            totalPedido = totalPedido.add(total);
            pedidoProdutoService.save(pp);
        }
        
        p.setTotal(totalPedido.add(entity.getFrete()));
        getService().save(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	
}
