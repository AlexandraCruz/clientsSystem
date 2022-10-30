package com.cliente.springboot.web.app.data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cliente.springboot.web.app.models.dao.IClienteDao;
import com.cliente.springboot.web.app.models.entity.Cliente;
import com.github.javafaker.Faker;

@Component
public class SampleDataLoader implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
	private final IClienteDao pagingAndSortingRepository;
	private final  Faker faker;
	
	private SampleDataLoader(IClienteDao  pagingAndSortingRepository, Faker faker) {
		this.pagingAndSortingRepository = pagingAndSortingRepository;
		this.faker = faker;
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Loading Sample Data..." );

		Date date_var = new Date();
		//create 100 rows of people in the database
		List<Cliente> people = IntStream.rangeClosed(1, 100)
				.mapToObj(i -> new Cliente(
				faker.name().firstName().toString(),
				faker.name().lastName().toString(),
				faker.internet().emailAddress().toString(),
				date_var,
						faker.internet().image()
				)).collect(Collectors.toList());
		
		pagingAndSortingRepository.saveAll(people);
				
	}
}
