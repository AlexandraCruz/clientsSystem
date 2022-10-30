package com.cliente.springboot.web.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cliente.springboot.web.app.models.entity.Cliente;

public interface IClienteService {
	/*Its implementing Facade Design Pattern*/
	
	/* Match all methods from IClienteDao*/

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public void save (Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	
}
