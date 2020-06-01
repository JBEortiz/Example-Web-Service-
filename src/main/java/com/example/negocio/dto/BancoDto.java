package com.example.negocio.dto;

import com.example.negocio.entity.Banco;

/**
 * Esta clase DTO para no mostrar al usuario la id
 * 
 * @author juan
 *
 */
public class BancoDto {
	private String nombre;
	private String sucursal;

	/**
	 * 
	 * @param Le paso como parametro un objeto de la clase entity para poder usar
	 *           con eficacia los DTO
	 */
	public BancoDto(Banco banco) {
		super();
		this.nombre = banco.getNombre();
		this.sucursal = banco.getSucursal();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

}
