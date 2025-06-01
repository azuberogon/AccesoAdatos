package es.marianasanz.acda.repaso.ut4.controller;

import es.marianasanz.acda.repaso.ut4.model.entity.Persona;
import es.marianasanz.acda.repaso.ut4.model.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//TODO No voy a usar los codigos que devuelven los metodos de servicio ya que no usamos paginas de errores.
// En caso de error cargariamos codigo especifico

//DEBEMOS INDICAR QUE ES UN CONTROLADOR
@Controller
//Si todos los métodos del controlador tienen una parte común en el mapeo de la petición podemos incluirla aquí
//@RequestMapping("/personas")
public class PersonaController {
    //Creamos el servicio que usaremos usando @Autowired
    @Autowired
    private PersonaService personaService;

    //Listado de todas las personas persistidas
    //Si hemos usado el @RequestMapping("/personas") al inicio solo necesitariamos @GetMappng({"", "/"})
    @GetMapping({"/personas", "/personas/"})
    public String listarPersonas(Model model){
        model.addAttribute("personas", personaService.findAll());
        return "personas/listar";
    }

    //Mostrar los datos de una sola personas partiendo de su id (nif)
    //El metodo tal como está no controla si existen datos con el nif suministrado
    //Si hemos usado el @RequestMapping("/personas") al inicio solo necesitariamos @GetMappng("/details")
    @GetMapping("/personas/details")
    //@RequestParam indica que es un parámetro que viene en la petición http ("/personas/details?id=el_id")
    public String detallesPersona(@RequestParam(name = "id") String id, Model model){
        model.addAttribute("persona", personaService.getById(id));
        return "personas/detalles";
    }

    //Guardar datos de una persona, sirve tanto para crear como para actualizar
    //De nuevo no comprobamos si un nif suministrado realmente existe
    //Si hemos usado el @RequestMapping("/personas") al inicio solo necesitariamos @GetMappng("/save")

    //Primero vamos con el mapeo de la peticón GET que carga el formulario
    @GetMapping("/personas/save")
    public String save(@RequestParam(name = "id", required = false) String id, Model model){
        //Si no nos pasan un parámetro "id" es que son datos nuevos e "id" será nulo
        if (id==null) {
            model.addAttribute("persona", new Persona());
            model.addAttribute("editar", false);
        }
        else {
            //Añadimos los datos de la persona y pasamos un flag indicando que es una edicion para no modificar el id
            model.addAttribute("persona", personaService.getById(id));
            model.addAttribute("editar", true);
        }
        //Llamamos a la plantilla de edición de datos personales
        return "personas/guardar";
    }
    //Ahora la petición POST con los datos introducidos en el formulario
    //Los datos de la persona ahora no vienen en la petición http sino como parte del modelo remitido mediante POST
    @PostMapping("/personas/save")
    public String save(@ModelAttribute(name = "persona") Persona persona, Model model){
        //TODO Antes de guardar los datos los validaríamos (no lo hago)
        personaService.save(persona);
        //Ahora podríamos llamar al controlador que carga el listado
        //return listarPersonas(model);
        //o mejor redirigir a la petición que gestiona el controlador que carga el listado
        return "redirect:/personas";
    }

    //Eliminar datos de una persona a partir de su id (nif)
    //Si hemos usado el @RequestMapping("/personas") al inicio solo necesitariamos @GetMappng("/delete")
    @GetMapping("/personas/delete")
    //En este caso el parámetro de la petición se llama "id" pero el parámetro del metodo es "nif"
    //La petición tendrá la forma "/personas/delete?id=IDENTIFICADOR" pero en el metodo accederé a el con "nif"
    public String borrar(@RequestParam(name = "id") String nif, Model model){
        //Podríamos usar el metodo que borra por persona
        //personaService.delete(personaService.getById(nif));
        //O el que borra por id
        personaService.deleteById(nif);
        //Cargamos el listado tras borrar el registro
        return "redirect:/personas";
    }

}
