package com.example.negocio.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.negocio.entity.Contactos;
import com.example.negocio.repository.ContactoRepository;
import com.example.negocio.service.ContactoService;

@Service
public class ContactoServiceImpl implements ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Contactos> findAllContacto() {
		return contactoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Contactos> finByIdContacto(Long id) {
		return contactoRepository.findById(id);
	}

	@Override
	@Transactional
	public Contactos saveContacto(Contactos contacto) {
		return contactoRepository.save(contacto);
	}

	@Override
	@Transactional
	public void deleteByIdContacto(Long id) {
		contactoRepository.deleteById(id);
	}

}
