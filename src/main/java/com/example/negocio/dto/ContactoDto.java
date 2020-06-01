package com.example.negocio.dto;

import com.example.negocio.entity.Contactos;

/**
 * Esta clase DTO para no mostrar al usuario la id
 * 
 * @author juanj
 *
 */
public class ContactoDto {

	private String nombre;

	private String direccion;

	/**
	 * 
	 * @param Le paso como parametro un objeto de la clase entity para poder usar
	 *           con eficacia los DTO
	 */

	public ContactoDto(Contactos contacto) {
		super();
		this.nombre = contacto.getNombre();
		this.direccion = contacto.getDireccion();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
