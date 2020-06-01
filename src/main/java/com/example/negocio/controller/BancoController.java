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

import com.example.negocio.dto.BancoDto;
import com.example.negocio.entity.Banco;
import com.example.negocio.service.BancoService;

@RestController
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	private BancoService bancoService;

	/**
	 * 
	 * @param id
	 * @return perteneciente BancoDTO busqueda por id
	 */

	@GetMapping("/{id}")
	public ResponseEntity<BancoDto> findBancos(@PathVariable Long id) {
		Optional<Banco> banco = bancoService.finByIdBanco(id);
		BancoDto bancoDto = new BancoDto(banco.get());
		if (bancoDto == null || id <= 0 || id == null) {
			return new ResponseEntity<BancoDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BancoDto>(bancoDto, HttpStatus.OK);

	}

	@GetMapping("/by/{id}")
	public ResponseEntity<?> findByIdBancos(@PathVariable Long id) {
		Optional<Banco> banco = bancoService.finByIdBanco(id);
		if (banco == null || id <= 0) {
			return new ResponseEntity<Banco>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(banco, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllBancos() {
		List<Banco> banco = new ArrayList<>();
		banco = (List<Banco>) bancoService.findAllBanco();
		if (banco.isEmpty()) {
			return new ResponseEntity<List<Banco>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<Banco>>(banco, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createBanco(@RequestBody Banco banco) {
		Banco banco1 = bancoService.saveBanco(banco);
		return ResponseEntity.status(HttpStatus.CREATED).body(banco1);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateBanco(@RequestBody Banco banco, @PathVariable Long id) {
		Optional<Banco> optional = bancoService.finByIdBanco(id);
		if (optional == null || id <= 0 || id == null) {
			return new ResponseEntity<Banco>(HttpStatus.NO_CONTENT);
		}
		Banco banco1 = optional.get();
		banco1.setNombre(banco.getNombre());
		banco1.setCapacidad(banco.getCapacidad());
		banco1.setSucursal(banco.getSucursal());
		bancoService.saveBanco(banco1);
		return new ResponseEntity<Banco>(banco1, HttpStatus.OK);

	}

	/**
	 * Este metodo me funciona solo con bancos no asociado a personas en caso de
	 * querer borrar un banco que este asociado a una persona en PersonaController
	 * el metodo DeleteBancoPersona
	 * 
	 */

	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> deleteBanco(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		bancoService.deleteByIdBanco(id);
		return ResponseEntity.noContent().build();
	}

}
