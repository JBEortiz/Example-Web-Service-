package com.example.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.negocio.entity.Contactos;

/**
 * Utilizo Jpa repository configurada por spring boot para mayor simplificar el
 * trabajo
 * 
 * @author juanj Esta es una clase que pertenece a SpringData cargaa los
 *         diferentes beans e invoca a los métodos del repositorio CRUD
 */
public interface ContactoRepository extends JpaRepository<Contactos, Long> {

}
