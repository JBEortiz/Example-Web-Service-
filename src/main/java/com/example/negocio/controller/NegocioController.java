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

import com.example.negocio.dto.NegocioDto;
import com.example.negocio.entity.Negocio;
import com.example.negocio.service.NegocioService;

@RestController
@RequestMapping("/negocio")
public class NegocioController {
	@Autowired
	private NegocioService negocioService;

	/**
	 * 
	 * @param id
	 * @return perteneciente NegocioDTO busqueda por id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<NegocioDto> findNegocio(@PathVariable Long id) {
		Optional<Negocio> negocio = negocioService.findByIdNegocio(id);
		NegocioDto negocioDto = new NegocioDto(negocio.get());
		if (negocioDto == null || id <= 0 || id == null) {
			return new ResponseEntity<NegocioDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NegocioDto>(negocioDto, HttpStatus.OK);

	}

	@GetMapping("/by/{id}")
	public ResponseEntity<?> findByIdNegocio(@PathVariable Long id) {
		Optional<Negocio> optional = negocioService.findByIdNegocio(id);
		if (optional == null || id <= 0 || id == null) {
			return new ResponseEntity<Negocio>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(optional, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllNegocios() {
		List<Negocio> negocio = new ArrayList<>();
		negocio = (List<Negocio>) negocioService.findAllNegocio();
		if (negocio.isEmpty()) {
			return new ResponseEntity<List<Negocio>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<Negocio>>(negocio, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createNegocio(@RequestBody Negocio negocio) {
		Negocio negocio1 = negocioService.saveNegocio(negocio);
		return ResponseEntity.status(HttpStatus.CREATED).body(negocio1);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> putNegocio(@RequestBody Negocio negocio, @PathVariable Long id) {
		Optional<Negocio> optional = negocioService.findByIdNegocio(id);
		if (optional == null || id <= 0 || id == null) {
			return new ResponseEntity<Negocio>(HttpStatus.NO_CONTENT);
		}
		Negocio negocio1 = optional.get();
		negocio1.setNombre(negocio.getNombre());
		negocio1.setNegocio(negocio.getNegocio());
		negocio1.setFacturacionAño(negocio.getFacturacionAño());
		negocio1.setCategoria(negocio.getCategoria());

		return ResponseEntity.status(HttpStatus.CREATED).body(negocioService.saveNegocio(negocio1));

	}

	/**
	 * Este metodo me funciona solo con bancos no asociado a personas en caso de
	 * querer borrar un negocio que este asociado a una persona en PersonaController
	 * el metodo DeleteNegocioPersona
	 * 
	 */

	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> deleteBanco(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		negocioService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
