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

import com.example.negocio.dto.ContactoDto;
import com.example.negocio.entity.Contactos;
import com.example.negocio.service.ContactoService;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

	@Autowired
	private ContactoService contactoService;

	/*
	 * Preciso utilizar el contacto.get() ya que viene de un optional
	 */

	@GetMapping("/{id}")
	public ResponseEntity<ContactoDto> findBancos(@PathVariable Long id) {
		Optional<Contactos> contacto = contactoService.finByIdContacto(id);
		ContactoDto contactoDto = new ContactoDto(contacto.get());
		if (contactoDto == null || id <= 0 || id == null) {
			return new ResponseEntity<ContactoDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ContactoDto>(contactoDto, HttpStatus.OK);

	}

	@GetMapping("/by/{id}")
	public ResponseEntity<?> findByIdContactos(@PathVariable Long id) {
		Optional<Contactos> contacto = contactoService.finByIdContacto(id);
		if (contacto == null || id <= 0) {
			return new ResponseEntity<Contactos>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(contacto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllContactos() {
		List<Contactos> contacto = new ArrayList<>();
		contacto = (List<Contactos>) contactoService.findAllContacto();
		if (contacto.isEmpty()) {
			return new ResponseEntity<List<Contactos>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contactos>>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createContacto(@RequestBody Contactos contacto) {
		Contactos contacto1 = contactoService.saveContacto(contacto);
		return ResponseEntity.status(HttpStatus.CREATED).body(contacto1);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> putContacto(@RequestBody Contactos contacto, @PathVariable Long id) {
		Optional<Contactos> optional = contactoService.finByIdContacto(id);
		if (optional == null || id <= 0 || id == null) {

			return new ResponseEntity<Contactos>(HttpStatus.NO_CONTENT);
		}
		Contactos contacto1 = optional.get();
		contacto1.setNombre(contacto1.getNombre());
		contacto1.setProductos(contacto.getProductos());
		contacto1.setDireccion(contacto.getDireccion());

		contactoService.saveContacto(contacto1);
		return new ResponseEntity<Contactos>(contacto1, HttpStatus.OK);

	}

	/**
	 * Este metodo me funciona solo con bancos no asociado a personas en caso de
	 * querer borrar un contacto que este asociado a una persona en
	 * PersonaController el metodo DeleteContactoPersona
	 * 
	 */

	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> deleteContacto(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		contactoService.deleteByIdContacto(id);
		return ResponseEntity.noContent().build();
	}
	/**
	 * Metodo utilizando ModelMapper fallido
	 * 
	 */

//	@GetMapping("/{id}")
//	public ResponseEntity<ContactoDto> findBancos(@PathVariable Long id) {
//		ModelMapper modelMapper = new ModelMapper();
//		Optional<Contactos> contacto = contactoService.finByIdContacto(id);
//		ContactoDto contactoDto = modelMapper.map(contacto, ContactoDto.class);
//		if (contactoDto == null || id <= 0 || id == null) {
//			return new ResponseEntity<ContactoDto>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<ContactoDto>(contactoDto, HttpStatus.OK);
//
//	}
}
