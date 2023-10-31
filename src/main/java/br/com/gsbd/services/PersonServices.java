package br.com.gsbd.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gsbd.data.vo.v1.PersonVO;
import br.com.gsbd.exceptions.ResourceNotFoundException;
import br.com.gsbd.mapper.DozerMapper;
import br.com.gsbd.model.Person;
import br.com.gsbd.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<PersonVO> findAll() {
		
		logger.info("Finding all people!");
		
		return DozerMapper.parseListsObjects(repository.findAll(), PersonVO.class);
	}
	

	public PersonVO findById(long id) {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	
	public PersonVO create(PersonVO personVO) {
		
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(personVO, Person.class);
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	
	public PersonVO update(PersonVO personVO) {
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(personVO.getId())
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );
		
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setGender(personVO.getGender());
		entity.setAddress(personVO.getAddress());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	
	public void delete(long id) {
		
		logger.info("Deleting one person!");
		
		Person entity = repository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );		
		
		repository.delete(entity);
	}
	
	
}
