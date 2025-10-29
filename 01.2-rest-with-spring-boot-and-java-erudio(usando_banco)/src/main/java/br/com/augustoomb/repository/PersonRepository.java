package br.com.augustoomb.repository;

import br.com.augustoomb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
