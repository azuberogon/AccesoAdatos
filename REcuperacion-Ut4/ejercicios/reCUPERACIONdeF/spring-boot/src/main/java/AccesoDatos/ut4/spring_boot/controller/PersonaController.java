package AccesoDatos.ut4.spring_boot.controller;

import AccesoDatos.ut4.spring_boot.model.entity.Persona;
import AccesoDatos.ut4.spring_boot.model.service.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/personas")
public class PersonaController {

    @Autowired
    private PersonasService personasService;


                //urrl
    @GetMapping("/personasALl")
    public String personasAll(Model model){
                                        //nombre de la lista / lista de las personas o de los objetos
        model.addAttribute("personas",personasService.personasList());
        return"listar";
    }
    @GetMapping("personaDetails")
    public String personaDetails(@RequestParam(name ="id") String id,Model model){
        model.addAttribute("persona",personasService.getById(id));
        return "redirect:/infoPersona";
    }


    @GetMapping("/addPersona")
    public String personaSave(@RequestParam( name = "id", required = false )String id,Model model ){
        //nombre de la lista / lista de las personas o de los objetos
        if (id == null){
            model.addAttribute("persona",new Persona());
            model.addAttribute("editar", false);
        }else{
            model.addAttribute("persona",personasService.getById(id));
            model.addAttribute("editar", true);
        }
        return"listar";
    }

    @GetMapping("/personaDelete")//Recoge datos expecificos de la URl como el id
    public String eliminarPersona(@RequestParam(name="id") String id, Model model){
        personasService.borrarPorPersona(personasService.getById(id));
        return "redirect:/personas";
    }

    @PostMapping("/savePersona")//Recoge datos compuestos del formulario como el objetoPersona
    public String savePersona(@ModelAttribute(name ="Persona")Persona persona,Model model){
        personasService.save(persona);

        return "redirect:/personas";
    }


}
