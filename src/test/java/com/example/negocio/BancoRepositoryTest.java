package com.example.negocio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.example.negocio.entity.Banco;
import com.example.negocio.repository.BancoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class BancoRepositoryTest {

	private Banco banco;

	@Autowired
	private BancoRepository cut;

	@BeforeEach
	public void setUp() {
		this.banco = new Banco();
		this.banco.setId(1L);
		this.banco.setNombre("Banck");
		this.banco.setCapacidad(100);
		this.banco.setSucursal("xxxx");
	}

	@Test
	public void createTest() {

		cut.save(banco);

		Assertions.assertEquals(cut.findById(this.banco.getId()), banco);
	}

	@Test
	public void readByTest() {
		Assertions.assertNull(cut.findById(banco.getId()));
		cut.save(banco);
		Assertions.assertEquals(cut.findById(banco.getId()), banco);

	}

	@Test
	public void findAllTest() {
		cut.save(banco);
		Assertions.assertEquals(1, cut.findAll().size());
	}

	@Test
	public void updateTest() {

		cut.save(banco);

		banco.getNombre();

		Banco nombre = new Banco();

		nombre.setNombre("banco");

		banco.setNombre("bank");

		cut.save(banco);

		Assertions.assertNotEquals(banco.getNombre(), nombre);

	}

	@Test
	public void deleteTest() {

		cut.save(banco);

		Assertions.assertEquals(1, cut.findAll().size());

		cut.delete(cut.save(banco));

		Assertions.assertTrue(cut.findAll().isEmpty());

	}

}
