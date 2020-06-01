package com.example.negocio.service;

import java.util.Optional;

import com.example.negocio.entity.Persona;

public interface PersonaService {

	Iterable<Persona> findAllPersona();

	Optional<Persona> findByIdPersona(Long id);

	Persona savePersona(Persona persona);

	void delete(Long id);

	Persona asociarBancoPersona(Long idBanco, Long idPersona);

	Persona asociarContactoPersona(Long idContacto, Long idPersona);

	Persona asociarNegocioPersona(Long idNegocio, Long idPersona);

	Persona deleteContactoPersona(Long idContacto, Long idPersona);

	Persona deleteNegocioPersona(Long idNegocio, Long idPersona);

	Persona deleteBancoPersona(Long idBanco, Long idPersona);

}
