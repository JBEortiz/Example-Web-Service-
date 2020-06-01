package com.example.negocio.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.negocio.entity.Banco;
import com.example.negocio.entity.Contactos;
import com.example.negocio.entity.Negocio;
import com.example.negocio.entity.Persona;
import com.example.negocio.repository.BancoRepository;
import com.example.negocio.repository.ContactoRepository;
import com.example.negocio.repository.NegocioRepository;
import com.example.negocio.repository.PersonaRepository;
import com.example.negocio.service.PersonaService;

/**
 * 
 * @author @Transactional(readOnly = true) Esta anotación indica una transaccion
 *         solo de lectura para el caso de save, update y delete utilizo solo la
 *         anotación
 *
 */
@Service
public class PersonaServiceImpl implements PersonaService {

	/*
	 * instancio las interfaces para poder utilizarlas en los metodos
	 * asociarXPersona y deleteXPersona
	 */
	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private BancoRepository bancoRepository;

	@Autowired
	private NegocioRepository negocioRepository;

	@Autowired
	private ContactoRepository contactoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findAllPersona() {
		return personaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> findByIdPersona(Long id) {
		return personaRepository.findById(id);
	}

	@Override
	@Transactional
	public Persona savePersona(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		personaRepository.deleteById(id);

	}

	/**
	 * Creo estos metodos para asociar por id una persona con un banco, negocio o
	 * contacto
	 */

	@Override
	@Transactional
	public Persona asociarBancoPersona(Long idBanco, Long idPersona) {

		Banco banco = bancoRepository.findById(idBanco).get();
		Persona persona = personaRepository.findById(idPersona).get();

		persona.getBancos().add(banco);
		personaRepository.save(persona);

		return persona;
	}

	@Override
	@Transactional
	public Persona asociarNegocioPersona(Long idNegocio, Long idPersona) {

		Negocio negocio = negocioRepository.findById(idNegocio).get();
		Persona persona = personaRepository.findById(idPersona).get();

		persona.getNegocios().add(negocio);
		personaRepository.save(persona);

		return persona;
	}

	@Override
	@Transactional
	public Persona asociarContactoPersona(Long idContacto, Long idPersona) {

		Contactos contacto = contactoRepository.findById(idContacto).get();
		Persona persona = personaRepository.findById(idPersona).get();

		persona.getContactos().add(contacto);
		personaRepository.save(persona);

		return persona;
	}

	/**
	 * Creo estos metodos para desasociar por id una persona con un banco, negocio o
	 * contacto
	 */

	@Override
	@Transactional
	public Persona deleteBancoPersona(Long idBanco, Long idPersona) {
		Banco banco = bancoRepository.findById(idBanco).get();
		Persona persona = personaRepository.findById(idPersona).get();

		persona.getBancos().remove(banco);
		personaRepository.save(persona);
		return persona;
	}

	@Override
	@Transactional
	public Persona deleteNegocioPersona(Long idNegocio, Long idPersona) {
		Negocio negocio = negocioRepository.findById(idNegocio).get();
		Persona persona = personaRepository.findById(idPersona).get();

		persona.getNegocios().remove(negocio);
		personaRepository.save(persona);

		return persona;
	}

	@Override
	@Transactional
	public Persona deleteContactoPersona(Long idContacto, Long idPersona) {
		Contactos contacto = contactoRepository.findById(idContacto).get();
		Persona persona = personaRepository.findById(idPersona).get();

		persona.getContactos().remove(contacto);
		personaRepository.save(persona);

		return persona;
	}

}
