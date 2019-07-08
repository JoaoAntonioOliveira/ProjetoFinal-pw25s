package br.edu.utfpr.pb.ecommerce.Ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.CompraProduto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CompraProdutoService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CrudService;

@Controller
@RequestMapping("compraProduto")
public class CompraProdutoController extends CrudController<CompraProduto, Long>{

	@Autowired
	private CompraProdutoService compraProdutoService;

//	@Autowired
//	private CompraService compraService;
//	
//	@Autowired
//	private ProdutoService produtoService;

	@Override
	protected CrudService<CompraProduto, Long> getService() {
		// TODO Auto-generated method stub
		return compraProdutoService;
	}

	@Override
	protected String getURL() {
		// TODO Auto-generated method stub
		return "compraProduto";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(CompraProduto compraProduto) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if(compraProduto != null) {
			modelAndView.addObject(compraProduto);
		}else {
			modelAndView.addObject(new CompraProduto());
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
	
	@PostMapping("ajax")
	public ResponseEntity<?> save(@Valid CompraProduto entity, BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public CompraProduto edit(@PathVariable Long id) {
		return getService().findOne(id);
	}
	
}
