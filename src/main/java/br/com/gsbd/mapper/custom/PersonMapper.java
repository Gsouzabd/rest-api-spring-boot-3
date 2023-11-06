package br.com.gsbd.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.gsbd.data.vo.v1.PersonVO;
import br.com.gsbd.model.Person;

@Service
public class PersonMapper {

	public PersonVO convertEntityToVo( Person person ) {
		PersonVO vo = new PersonVO();
		vo.setKey(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		
		return vo;

	}
	
	
	public Person convertVoToEntity( PersonVO person ) {
		Person vo = new Person();
		vo.setId(person.getKey());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
//		vo.setBirthDay(new Date());
		vo.setGender(person.getGender());

		return vo;
	}
}
