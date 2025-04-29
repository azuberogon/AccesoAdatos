package com.example.demo.dao;

import com.example.demo.Persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IPersonaDao extends JpaRepository<Persona,String> {


}
