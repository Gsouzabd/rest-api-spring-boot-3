package br.com.gsbd.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gsbd.data.vo.v1.PersonVO;
import br.com.gsbd.data.vo.v2.PersonVOV2;
import br.com.gsbd.exceptions.ResourceNotFoundException;
import br.com.gsbd.mapper.DozerMapper;
import br.com.gsbd.mapper.custom.PersonMapper;
import br.com.gsbd.model.Person;
import br.com.gsbd.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper personMapper;

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
	
	public PersonVOV2 createV2(PersonVOV2 personVOV2) {
		
		logger.info("Creating one person with v2!");
				
		var entity = personMapper.convertVoToEntity(personVOV2);
		
		var voV2 = personMapper.convertEntityToVo(repository.save(entity));
		
		return voV2;
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
