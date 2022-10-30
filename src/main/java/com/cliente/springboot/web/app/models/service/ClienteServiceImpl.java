package com.cliente.springboot.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cliente.springboot.web.app.models.dao.IClienteDao;
import com.cliente.springboot.web.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl  implements IClienteService {

	@Autowired
	private IClienteDao iClienteDao;
	
	@Override
	@Transactional(readOnly=true) /*@Transactional  AOP Aspect Oriented Programming  Indicates that this methods is for query only and readOnly, and transactional. This anotation takes the method content and wrapper inside of transaction   */
	public List<Cliente> findAll() {
		return (List<Cliente>) iClienteDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Cliente> findAll(Pageable pageable) {
		return iClienteDao.findAll(pageable);
	}
	
	@Override
	@Transactional//para insertar nuevo registro
	/* 	@Transactional AOP Aspect Oriented Programming  This annotation takes the method content and wrapper inside of transaction   */
	public void save(Cliente cliente) {
		
		iClienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly=true) /* @Transactional  AOP Aspect Oriented Programming Indicates that this methods is for query only and readOnly, and transactional. This anotation takes the method content and wrapper inside of transaction   */
	public Cliente findOne(Long id) {
		
		return iClienteDao.findById(id).orElse(null);/*Because .findById(id) returns an Optional Object */
	}

	@Override
	@Transactional /* 	@Transactional  AOP Aspect Oriented Programming  This annotation takes the method content and wrapper inside of transaction   */
	public void delete(Long id) {
		
		iClienteDao.deleteById(id);
	}
}