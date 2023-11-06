package br.com.gsbd.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.gsbd.controllers.PersonController;
import br.com.gsbd.data.vo.v1.PersonVO;
import br.com.gsbd.exceptions.RequiredObjectIsNullException;
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
	
	public List<PersonVO> findAll()  {
		
		logger.info("Finding all people!");
		
		var persons = DozerMapper.parseListsObjects(repository.findAll(), PersonVO.class);
		
		persons
			.stream()
			.forEach( person -> {
				try {
					person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
				
		return persons;
	}
	

	public PersonVO findById(long id) throws Exception {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );
		
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findAll()).withRel("All Persons"));
		
		return vo;
	}
	
	
	public PersonVO create(PersonVO personVO) throws Exception {
		
		if (personVO == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(personVO, Person.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
//	public PersonVOV2 createV2(PersonVOV2 personVOV2) {
//		
//		logger.info("Creating one person with v2!");
//				
//		var entity = personMapper.convertVoToEntity(personVOV2);
//		
//		var voV2 = personMapper.convertEntityToVo(repository.save(entity));
//		
//		return voV2;
//	}
	
	
	public PersonVO update(PersonVO personVO) throws Exception {
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(personVO.getKey())
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );
		
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setGender(personVO.getGender());
		entity.setAddress(personVO.getAddress());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	
	public void delete(long id) {
		
		logger.info("Deleting one person!");
		
		Person entity = repository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("No resources found for this id.") );		
		
		repository.delete(entity);
	}
	
	
}
