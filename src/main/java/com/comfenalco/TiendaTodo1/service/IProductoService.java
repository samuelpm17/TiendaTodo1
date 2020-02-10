package com.comfenalco.TiendaTodo1.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.comfenalco.TiendaTodo1.model.entity.Producto;

public interface IProductoService {
	
	public void guardar(Producto producto);
	
	public void vender(Long id, Integer cantidad);
	
	public List<Producto> buscar();
	
	public Producto buscarId(Long id);
	
	public String guardarImagen(MultipartFile archivo);
}
