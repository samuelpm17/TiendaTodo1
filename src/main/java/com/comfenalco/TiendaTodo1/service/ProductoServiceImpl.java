package com.comfenalco.TiendaTodo1.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.comfenalco.TiendaTodo1.model.entity.Producto;
import com.comfenalco.TiendaTodo1.model.persistence.IProductoDao;

@Service
public class ProductoServiceImpl implements IProductoService {
	@Autowired
	IProductoDao productoDao;

	@Value("${rutaDirectorioLocal}")
	String ruta;

	public void guardar(Producto producto) {
		productoDao.save(producto);
	}

	public List<Producto> buscar() {
		return productoDao.findAll();
	}

	public Producto buscarId(Long id) {
		return productoDao.findById(id).get();
	}

	public void vender(Long id, Integer cantidad) {
		Producto producto = productoDao.findById(id).get();
		producto.setCantidad(producto.getCantidad() - cantidad);
		productoDao.save(producto);
	}

	@Override
	public String guardarImagen(MultipartFile archivo) {

		if (!archivo.isEmpty()) {
			byte[] bytes;
			try {
				bytes = archivo.getBytes();
				File rutaServidor = new File(ruta);
				String ruta = rutaServidor.getPath() + File.separator + archivo.getOriginalFilename();
				if (!rutaServidor.exists())
					rutaServidor.mkdirs();

				File uploadFile = new File(ruta);

				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				outputStream.write(bytes);
				outputStream.close();
				
				return ruta;

			} catch (IOException e) {
				return null;
			}

		}
		return null;
	}

}
