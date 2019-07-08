package br.edu.utfpr.pb.ecommerce.Ecommerce.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Fornecedor;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CrudService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.FornecedorService;

@Controller
@RequestMapping("fornecedor")
public class FornecedorController extends CrudController<Fornecedor, Integer>{

	@Autowired
	private FornecedorService fornecedorService;

	@Override
	protected CrudService<Fornecedor, Integer> getService() {
		// TODO Auto-generated method stub
		return fornecedorService;
	}

	@Override
	protected String getURL() {
		// TODO Auto-generated method stub
		return "fornecedor";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if(fornecedor != null) {
			modelAndView.addObject(fornecedor);
		}else {
			modelAndView.addObject(new Fornecedor());
		}
		return modelAndView;
	}

	@Override
	@GetMapping("{id}")
	protected ModelAndView form(Integer id) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		
		modelAndView.addObject(this.getService().findOne(id));
		return modelAndView;
	}
	
	@PostMapping("ajax")
	public ResponseEntity<?> save(@Valid Fornecedor entity, BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public Fornecedor edit(@PathVariable Integer id) {
		return getService().findOne(id);
	}
	
	@Override
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Fornecedor> list = this.getService().findAll(
				PageRequest.of(currentPage -1, pageSize));
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);
		
		if(list.getTotalPages()>0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
	}
	
}
