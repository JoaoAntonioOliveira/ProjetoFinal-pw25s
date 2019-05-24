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

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Categoria;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CategoriaService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CrudService;

@Controller
@RequestMapping("categoria")
public class CategoriaController extends CrudController<Categoria, Integer>{

	@Autowired
	private CategoriaService categoriaService;

	@Override
	protected CrudService<Categoria, Integer> getService() {
		// TODO Auto-generated method stub
		return categoriaService;
	}

	@Override
	protected String getURL() {
		// TODO Auto-generated method stub
		return "categoria";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Categoria categoria) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if(categoria != null) {
			modelAndView.addObject(categoria);
		}else {
			modelAndView.addObject(new Categoria());
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
	public ResponseEntity<?> save(@Valid Categoria entity, BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public Categoria edit(@PathVariable Integer id) {
		return getService().findOne(id);
	}
	
}
