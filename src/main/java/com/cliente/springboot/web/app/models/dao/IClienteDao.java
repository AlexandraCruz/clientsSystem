package com.cliente.springboot.web.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cliente.springboot.web.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> { /*PagingAndSortingRepository class extends CrudRepository*/

	
		
}