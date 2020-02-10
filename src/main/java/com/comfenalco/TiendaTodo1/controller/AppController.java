package com.comfenalco.TiendaTodo1.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.comfenalco.TiendaTodo1.model.entity.Producto;
import com.comfenalco.TiendaTodo1.service.IProductoService;

@Controller
@SessionAttributes("producto")
public class AppController {
	
	@Autowired
	private IProductoService productoService;
		
	@GetMapping({"/index","/"})
	public String galeria(Model model) {

		model.addAttribute("titulo", "Galeria");
		model.addAttribute("productos", productoService.buscar());
		return "galeria";
	}

	@GetMapping("/listar")
	public String listar(Model model) {

		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.buscar());
		return "listar";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {

		Producto producto = null;
		
		if(id > 0) {
			producto = productoService.buscarId(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("producto", producto);
		model.put("titulo", "Editar Producto");
		
		return "form";
	}
	
	@GetMapping("/form")
	public String crear(Model model) {

		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Formulario de Producto");
		return "form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Producto producto, BindingResult result, Model model, SessionStatus status) throws IOException {
		MultipartFile archivo = producto.getArchivo();

		if(result.hasErrors()) {
			model.addAttribute("titulo", "Editar Producto");
			return "form";
		}
		
		String imagen = productoService.guardarImagen(archivo);
		if(imagen != null) {
			producto.setImagen("/images/"+archivo.getOriginalFilename());
		}else {
			producto.setImagen("/images/sinimagen.png");
		}
		producto.setCreateAt(new Date());;
		productoService.guardar(producto);
		status.setComplete();
		return "redirect:listar";
	}
	
}
