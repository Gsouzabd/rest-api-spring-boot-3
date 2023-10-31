package br.com.gsbd.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gsbd.exceptions.ResourceNotFoundException;
import br.com.gsbd.model.Person;
import br.com.gsbd.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		
		logger.info("Finding all people!");
		
		return repository.findAll();
	}
	

	public Person findById(long id) {
		
		logger.info("Finding one person!");
		
		return repository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );
	}
	
	
	public Person create(Person person) {
		
		logger.info("Creating one person!");
		
		return repository.save(person);
	}
	
	
	public Person update(Person person) {
		
		logger.info("Updating one person!");
		
		Person entity = repository.findById(person.getId())
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());
		
		return repository.save(entity);
	}
	
	
	public void delete(long id) {
		
		logger.info("Deleting one person!");
		
		Person entity = repository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );		
		
		repository.delete(entity);
	}
	
	
}
