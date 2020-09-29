package com.jackcmeyer.java15demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    JdbcTemplate jdbcTemplate;
    PersonRepository personRepository;

    public PersonService(JdbcTemplate jdbcTemplate, PersonRepository personRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.personRepository = personRepository;
    }

    public void save(PersonRequest personRequest) {

    }

    public Person findById(String id) {
        return jdbcTemplate.queryForObject("SELECT * \n" +
                "FROM PERSON \n" +
                "WHERE id = " + id, Person.class);
    }

}
