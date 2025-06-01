package AccesoDatos.ut4.spring_boot.Controller;

import AccesoDatos.ut4.spring_boot.Entity.Persona;
import AccesoDatos.ut4.spring_boot.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/crud")
@RequestMapping("/crud")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping(path="/addPersona")
    public String addNewUser(Model model){
        Persona persona = new Persona(); // se crea un objeto vacío
        model.addAttribute("persona",persona);// se envía al HTML
        return "addPersona";// Thymeleaf busca "addPersona.html" en templates
    }

    @PostMapping(path="/add")
    public String addNewUser(@ModelAttribute Persona persona, Model model){
        personaService.guardar(persona);
        return "redirect:allPersonas";
    }






}
