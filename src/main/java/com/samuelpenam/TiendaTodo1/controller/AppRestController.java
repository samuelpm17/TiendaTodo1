package com.samuelpenam.TiendaTodo1.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samuelpenam.TiendaTodo1.service.IProductoService;

@RestController
public class AppRestController {
	@Autowired
	IProductoService productoService; 

	@PostMapping("/vender")
	public void vender(@RequestParam(value = "id") Long id, @RequestParam(value = "cantidad") Integer cantidad,
			Model model) throws IOException {
		productoService.vender(id, cantidad);
		model.addAttribute("titulo","Galeria");
		model.addAttribute("productos", productoService.buscar());
	}
}
