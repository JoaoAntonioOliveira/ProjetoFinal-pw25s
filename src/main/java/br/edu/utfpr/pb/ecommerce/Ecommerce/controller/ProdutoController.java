package br.edu.utfpr.pb.ecommerce.Ecommerce.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.pb.ecommerce.Ecommerce.model.Produto;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CategoriaService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.CrudService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.MarcaService;
import br.edu.utfpr.pb.ecommerce.Ecommerce.service.ProdutoService;

@Controller
@RequestMapping("produto")
public class ProdutoController extends CrudController<Produto, Long>{

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private MarcaService marcaService;

	@Override
	protected CrudService<Produto, Long> getService() {
		// TODO Auto-generated method stub
		return produtoService;
	}

	@Override
	protected String getURL() {
		// TODO Auto-generated method stub
		return "produto";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Produto produto) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if(produto != null) {
			modelAndView.addObject(produto);
		}else {
			modelAndView.addObject(new Produto());
		}
		return modelAndView;
	}

	@Override
	@GetMapping("{id}")
	protected ModelAndView form(@PathVariable Long id) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/formVisualizar");
		
		modelAndView.addObject("produto", this.getService().findOne(id));
		return modelAndView;
	}
	
	@PostMapping("ajax")
	public ResponseEntity<?> save(@Valid Produto entity, BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public Produto edit(@PathVariable Long id) {
		return getService().findOne(id);
	}
	
	@Override
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Produto> list = this.getService().findAll(
				PageRequest.of(currentPage -1, pageSize));
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);

		modelAndView.addObject("categorias", categoriaService.findAll());
		modelAndView.addObject("marcas", marcaService.findAll());
		
		if(list.getTotalPages()>0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
	}
	
	@GetMapping("pageComprar")
	public ModelAndView listComprar(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Produto> list = this.getService().findAll(
				PageRequest.of(currentPage -1, pageSize));
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/listComprar");
		modelAndView.addObject("list", list);
		
		modelAndView.addObject("categorias", categoriaService.findAll());
		modelAndView.addObject("marcas", marcaService.findAll());
		
		if(list.getTotalPages()>0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
	}
	
	//Carregar Imagem
	@GetMapping("/imagem/{produtoId}")
    public ResponseEntity<?> getProductImages(HttpServletRequest request,
                                              @PathVariable Long produtoId
    ) {
        List<byte[]> imagem = new ArrayList<>();

        Produto produto = getService().findOne(produtoId);
        File file = new File(produto.getImagem());
        
           	if (produto != null) {
                try {
                    imagem.add(Files.readAllBytes(file.toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        return new ResponseEntity<>(imagem, HttpStatus.OK);
    }
	

	//método para salvar com upload de arquivo
	@PostMapping("upload")
	public ResponseEntity<?> save(@Valid Produto entity, BindingResult result, 
			@RequestParam("anexos") MultipartFile[] anexos,
			HttpServletRequest request){
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		Produto p = getService().save(entity);
		
		if(anexos.length > 0 && !anexos[0].getOriginalFilename().isEmpty()) {
			saveFile(entity.getId(), anexos, request, p);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	private void saveFile(Long id, MultipartFile[] anexos, HttpServletRequest request, Produto produto) {
		// TODO Auto-generated method stub
		File dir = new File("C:\\trabalhoFinal\\imagens\\");
		if(!dir.exists()) { //verifica se o diretorio de armazenamento existe
			dir.mkdirs(); //não existindo, cria o diretório
		}
		
		String caminhoAnexo = "C:\\trabalhoFinal\\imagens\\";
		
		int i = 0;
		for(MultipartFile anexo: anexos) {
			i++;
			String extensao = anexo.getOriginalFilename().substring(
					anexo.getOriginalFilename().lastIndexOf("."),
					anexo.getOriginalFilename().length());
			
			String nomeArquivo = id + "_" + i + extensao;
			
			try {
				FileOutputStream fileOut = new FileOutputStream(
						new File(caminhoAnexo + nomeArquivo));
				
				BufferedOutputStream stream = new BufferedOutputStream(fileOut);
				stream.write(anexo.getBytes());
				stream.close();

				produto.setImagem(caminhoAnexo + nomeArquivo);
				getService().save(produto);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
