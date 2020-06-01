package com.example.negocio.dto;

import com.example.negocio.entity.Negocio;

/**
 * Esta clase DTO para no mostrar al usuario la id
 * 
 * @author juan
 *
 */
public class NegocioDto {

	private String nombre;

	private int categoria;

	/**
	 * 
	 * @param Le paso como parametro un objeto de la clase entity para poder usar
	 *           con eficacia los DTO
	 */
	public NegocioDto(Negocio negocio) {
		super();
		this.nombre = negocio.getNombre();
		this.categoria = negocio.getCategoria();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

}
