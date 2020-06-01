package com.example.negocio.service;

import java.util.Optional;

import com.example.negocio.entity.Banco;

public interface BancoService {

	Iterable<Banco> findAllBanco();

	Optional<Banco> finByIdBanco(Long id);

	Banco saveBanco(Banco banco);

	void deleteByIdBanco(Long id);
}
