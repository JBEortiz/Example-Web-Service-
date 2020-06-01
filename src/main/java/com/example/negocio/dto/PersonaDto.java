package com.example.negocio.dto;

import java.util.List;

import com.example.negocio.entity.Contactos;
import com.example.negocio.entity.Negocio;
import com.example.negocio.entity.Persona;

/**
 * Esta clase DTO para no mostrar al usuario la id
 * 
 * @author juan Tampoco la lista de bancos a la que pertenece
 */
public class PersonaDto {

	private String nombre;

	private List<Negocio> negocios;

	private List<Contactos> contactos;

	/**
	 * 
	 * @param Le paso como parametro un objeto de la clase entity para poder usar
	 *           con eficacia los DTO
	 */
	public PersonaDto(Persona persona) {
		this.nombre = persona.getNombre();
		this.negocios = persona.getNegocios();
		this.contactos = persona.getContactos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Negocio> getNegocios() {
		return negocios;
	}

	public void setNegocios(List<Negocio> negocios) {
		this.negocios = negocios;
	}

	public List<Contactos> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contactos> contactos) {
		this.contactos = contactos;
	}

}
