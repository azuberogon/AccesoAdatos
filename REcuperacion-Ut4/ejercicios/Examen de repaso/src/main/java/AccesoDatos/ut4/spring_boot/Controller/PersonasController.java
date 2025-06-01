package AccesoDatos.ut4.spring_boot.Controller;

import AccesoDatos.ut4.spring_boot.Model.Entity.Persona;
import AccesoDatos.ut4.spring_boot.Model.Services.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("personas")
public class PersonasController {
    @Autowired
    private PersonasService personasService;

//    @GetMapping("/crear")
//    public String crearPersona(@RequestMapping Persona){
//        return "Guardar";
//    }

    @GetMapping("/personas")
    public String listarPersonas(Model model){
        model.addAttribute("Persona",personasService.findAll());
        return "Listar";
    }

    @DeleteMapping("/delete")
    public String deletePersona(@RequestParam(name = "id")String id, Model model){
       personasService.deletePorId(id);
       return "redirect:listar";
    }

    @GetMapping("/details")
    public String details(@RequestParam(name = "id") String id, Model model ){
        model.addAttribute("Persona",personasService.getByID(id));
        return "Detalles";
    }
    @GetMapping("/crear")
    public String crearpersona(@RequestParam(name ="id" , required = false ) String id , Model model){

        if (id == null){
            model.addAttribute("Persona",new Persona());//el objeto quiere ser creado no existe lo creamos
            model.addAttribute("editar",false);

        }else {

            model.addAttribute("persona",personasService.getByID(id));//el objeto ya existe se le pasa el objeto
            model.addAttribute("editar",true);
        }

        return "redirect:Listar";
    }

    @PostMapping("/crear")
    public String save (@ModelAttribute(name ="Persona") Persona persona, Model model){
        personasService.crearPersonas(persona);
        return "redirect:/Listar";
    }






}
