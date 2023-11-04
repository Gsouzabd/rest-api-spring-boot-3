package br.com.gsbd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gsbd.data.vo.v1.PersonVO;
import br.com.gsbd.data.vo.v2.PersonVOV2;
import br.com.gsbd.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
		
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public List<PersonVO> findAll() {
		
		return service.findAll();
		
	}
	
	
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public PersonVO findById( @PathVariable(value = "id") Long id ) throws Exception{
		
		return service.findById(id);
		
	}
	

	@PostMapping(consumes={MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public PersonVO create( @RequestBody PersonVO person) {
		return service.create(person);
	}
	
	@PostMapping(value = "/v2",
			consumes={MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public PersonVOV2 createV2( @RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}
	
	
	@PutMapping(consumes={MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public PersonVO update( @RequestBody PersonVO person) {
		return service.update(person);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete( @PathVariable(value="id") Long id ) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
