package com.example.negocio.service;

import java.util.Optional;

import com.example.negocio.entity.Contactos;

public interface ContactoService {

	Iterable<Contactos> findAllContacto();

	Optional<Contactos> finByIdContacto(Long id);

	Contactos saveContacto(Contactos contacto);

	void deleteByIdContacto(Long id);

}
