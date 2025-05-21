package es.masanz.UT5.Ejercicio2.demo.dao;

import es.masanz.UT5.Ejercicio2.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDAO extends CrudRepository<User, Integer> {
//se usa el interface para poder pasar los parametros User e Integer porque se hara propia la clase, usando sus metodos 
}
