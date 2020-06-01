package com.example.negocio.service;

import java.util.Optional;

import com.example.negocio.entity.Negocio;

public interface NegocioService {

	Iterable<Negocio> findAllNegocio();

	Optional<Negocio> findByIdNegocio(Long id);

	Negocio saveNegocio(Negocio negocio);

	void delete(Long id);

}
