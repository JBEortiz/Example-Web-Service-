package com.example.negocio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.negocio.dto.PersonaDto;
import com.example.negocio.entity.Persona;
import com.example.negocio.service.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@GetMapping("/{id}")
	public ResponseEntity<PersonaDto> findPersona(@PathVariable Long id) {
		Optional<Persona> persona = personaService.findByIdPersona(id);
		PersonaDto personaDto = new PersonaDto(persona.get());
		if (personaDto == null || id <= 0 || id == null) {
			return new ResponseEntity<PersonaDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<PersonaDto>(personaDto, HttpStatus.OK);

	}

	@GetMapping("/by/{id}")
	public ResponseEntity<?> findByIdPersona(@PathVariable Long id) {
		Optional<Persona> persona = personaService.findByIdPersona(id);
		if (persona.isPresent() || id <= 0) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(persona, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllPersona() {
		List<Persona> persona = new ArrayList<>();
		persona = (List<Persona>) personaService.findAllPersona();
		if (persona.isEmpty()) {
			return new ResponseEntity<List<Persona>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<Persona>>(persona, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createPersona(@RequestBody Persona persona1) {
		Persona persona = personaService.savePersona(persona1);
		return ResponseEntity.status(HttpStatus.CREATED).body(persona);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> putPersona(@RequestBody Persona persona, @PathVariable Long id) {
		Optional<Persona> optional = personaService.findByIdPersona(id);
		if (optional.isPresent() || id <= 0 || id == null) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
		}
		Persona persona1 = optional.get();
		persona1.setNombre(persona.getNombre());

		personaService.savePersona(persona1);
		return new ResponseEntity<Persona>(persona1, HttpStatus.OK);

	}

	/*
	 * Este metodo borra una persona y todo asociado a ella en cascada
	 * 
	 */

	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> deletePersona(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		personaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * Metodos actualizados asociarbanco/negocio/contacto
	 */
	@PutMapping("/asociarBanco/{idBanco}/{idPersona}")
	public ResponseEntity<Persona> addBanco(@PathVariable Long idBanco, @PathVariable Long idPersona) {
		Persona persona = personaService.asociarBancoPersona(idBanco, idPersona);

		if (idBanco <= 0 || idBanco == null || idPersona <= 0 || idPersona == null) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(persona);

	}

	@PutMapping("/asociarNegocio/{idNegocio}/{idPersona}")
	public ResponseEntity<Persona> addNegocio(@PathVariable Long idNegocio, @PathVariable Long idPersona) {
		Persona persona = personaService.asociarNegocioPersona(idNegocio, idPersona);

		if (idNegocio <= 0 || idNegocio == null || idPersona <= 0 || idPersona == null) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(persona);

	}

	@PutMapping("/asociarContacto/{idContacto}/{idPersona}")
	public ResponseEntity<Persona> addContacto(@PathVariable Long idContacto, @PathVariable Long idPersona) {
		Persona persona = personaService.asociarContactoPersona(idContacto, idPersona);

		if (idContacto <= 0 || idContacto == null || idPersona <= 0 || idPersona == null) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(persona);

	}

	/*
	 * Utilizo estos metodos para borrar de una persona un negocio, un banco y un
	 * contacto.
	 * 
	 */
	@DeleteMapping("/deleteBanco/{idBanco}/{idPersona}")
	public ResponseEntity<?> deleteContactoPersona(@PathVariable Long idBanco, @PathVariable Long idPersona) {
		Persona persona = personaService.deleteBancoPersona(idBanco, idPersona);

		if (idBanco <= 0 || idBanco == null || idPersona <= 0 || idPersona == null) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePersona(persona));

	}

	@DeleteMapping("/deleteNegocio/{idNegocio}/{idPersona}")
	public ResponseEntity<?> deleteBancoPersona(@PathVariable Long idNegocio, @PathVariable Long idPersona) {
		Persona persona = personaService.deleteNegocioPersona(idNegocio, idPersona);

		if (idNegocio <= 0 || idNegocio == null || idPersona <= 0 || idPersona == null) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePersona(persona));

	}

	@DeleteMapping("/deleteContacto/{idContacto}/{idPersona}")
	public ResponseEntity<?> deleteNegocioPersona(@PathVariable Long idContacto, @PathVariable Long idPersona) {
		Persona persona = personaService.deleteContactoPersona(idContacto, idPersona);

		if (idContacto <= 0 || idContacto == null || idPersona <= 0 || idPersona == null) {
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePersona(persona));

	}

	/*
	 * Otro emplejo para estos metodos de añadir a una persona un negocio, un banco
	 * y un contacto o VARIOS pero es mucho mas engorroso a la hora de trabajar con
	 * el
	 */
//	@PutMapping("/ayadirBanco/{id}")
//	public ResponseEntity<?> addBanco(@RequestBody List<Banco> bancos, @PathVariable Long id) {
//		Optional<Persona> optional = personaService.findByIdPersona(id);
//		if (!optional.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		Persona persona = optional.get();
//		bancos.forEach(e -> {
//			persona.setBancos(bancos);
//		});
//		return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePersona(persona));
//
//	}
//
//	@PutMapping("/ayadirNegocio/{id}")
//	public ResponseEntity<?> addNegocio(@RequestBody List<Negocio> negocios, @PathVariable Long id) {
//		Optional<Persona> optional = personaService.findByIdPersona(id);
//		if (!optional.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		Persona persona = optional.get();
//		negocios.forEach(e -> {
//			persona.setNegocios(negocios);
//			;
//		});
//		return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePersona(persona));
//	}
//
//	@PutMapping("/ayadirContacto/{id}")
//	public ResponseEntity<?> addContacto(@RequestBody List<Contactos> contacto, @PathVariable Long id) {
//		Optional<Persona> optional = personaService.findByIdPersona(id);
//		if (!optional.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		Persona persona = optional.get();
//		contacto.forEach(e -> {
//			persona.setContactos(contacto);
//		});
//		return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePersona(persona));
//
//	}

	/*
	 * 
	 * 
	 */

}
