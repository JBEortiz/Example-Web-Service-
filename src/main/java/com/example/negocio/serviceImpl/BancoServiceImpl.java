package com.example.negocio.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.negocio.entity.Banco;
import com.example.negocio.repository.BancoRepository;
import com.example.negocio.service.BancoService;

@Service

public class BancoServiceImpl implements BancoService {

	@Autowired
	private BancoRepository bancoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Banco> findAllBanco() {
		return bancoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Banco> finByIdBanco(Long id) {
		return bancoRepository.findById(id);
	}

	@Override
	@Transactional
	public Banco saveBanco(Banco banco) {
		return bancoRepository.save(banco);
	}

	@Override
	@Transactional
	public void deleteByIdBanco(Long id) {
		bancoRepository.deleteById(id);
	}

}
