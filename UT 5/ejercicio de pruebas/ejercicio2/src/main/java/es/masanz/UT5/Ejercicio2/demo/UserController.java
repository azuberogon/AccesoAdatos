package es.masanz.UT5.Ejercicio2.demo;

import es.masanz.UT5.Ejercicio2.demo.dao.IUserDAO;
import es.masanz.UT5.Ejercicio2.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    private IUserDAO iUserDAO;

    @PostMapping(path="/add")
    public String addNewUser(@RequestParam String name,@RequestParam String email) {
        User usuario = new User();
        usuario.setNombre(name);
        usuario.setEmail(email);
        iUserDAO.save(usuario);
        return "saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return iUserDAO.findAll();
    }
}
