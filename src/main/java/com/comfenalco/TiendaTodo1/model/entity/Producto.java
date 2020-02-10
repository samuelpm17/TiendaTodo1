package com.comfenalco.TiendaTodo1.model.entity;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	private static final long serialVersionUID = -5807439210407430558L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;
	
	@Min(value=0, message="Cantidad debe ser mayor a 0")
	@NotNull
	private Integer cantidad;

	@NotEmpty
	private String descripcion;

	private String imagen = "/images/sinimagen.png";
	
	@Transient
	private MultipartFile archivo;

	
	public MultipartFile getArchivo() {
		return archivo;
	}

	public void setArchivo(MultipartFile archivo) {
		this.archivo = archivo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Column(name = "create_at")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;

	public Integer getCantidad() {
		return cantidad;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	

	public void setDescripcion(String email) {
		this.descripcion = email;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
