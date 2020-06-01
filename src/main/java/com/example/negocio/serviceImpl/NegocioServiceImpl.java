package com.example.negocio.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.negocio.entity.Negocio;
import com.example.negocio.repository.NegocioRepository;
import com.example.negocio.service.NegocioService;

@Service
public class NegocioServiceImpl implements NegocioService {

	@Autowired
	private NegocioRepository negocioRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Negocio> findAllNegocio() {
		return negocioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Negocio> findByIdNegocio(Long id) {
		return negocioRepository.findById(id);
	}

	@Override
	@Transactional
	public Negocio saveNegocio(Negocio negocio) {
		return negocioRepository.save(negocio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		negocioRepository.deleteById(id);
	}

}
