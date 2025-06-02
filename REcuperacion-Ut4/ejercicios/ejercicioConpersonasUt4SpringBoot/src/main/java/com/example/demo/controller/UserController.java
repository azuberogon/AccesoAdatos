package com.example.demo.controller;

import com.example.demo.Persona.Persona;
import com.example.demo.Service.PersonaService;
import com.example.demo.dao.IPersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/persona")
public class UserController {

    @Autowired
    private PersonaService personaService;


    @GetMapping(path="/listar")
    public String listar(Model model){
        model.addAttribute("personas",personaService.listarPersona());
        return "listar";
    }


    @GetMapping(path="/crear")
    public String crearPersona(@RequestParam(name = "id")String id, Model model){
        if (id != null){
            model.addAttribute("persona", personaService.save(new Persona()));
            model.addAttribute("editar",false );

        }else{
            model.addAttribute("persona", personaService.getById(id));
            model.addAttribute("editar",true);

        }
        model.addAttribute("personas",personaService.listarPersona());
        return "listar";
    }

    @PostMapping("/crear")
    public String crearPersona(@ModelAttribute(name = "persona") Persona persona, Model model){
        if (persona!=null){
            personaService.save(persona);
        }else System.out.println("errror en la inserccion");

        return "listar";
    }
































    @PostMapping(path="/add")
    public String addNewUser(@ModelAttribute Persona persona, Model model){

//        Persona per = new Persona();
//        per.setNif(persona.getNif());
//        per.setNss(persona.getNss());
//        per.setApellido(persona.getApellido());
//        per.setAnyoNacimiento(persona.getAnyoNacimiento());
//        per.setFechaAlta(persona.getFechaAlta());
        personaService.save(persona);
        return "redirect:/crud/all";//como se lla
    }
    @GetMapping(path="/add")
    public String addNewUser(Model model){
        Persona per = new Persona();
        model.addAttribute("persona",per);
        return "add";//carga la plantilla perosna el nombre de
        //return findALll; te te ejecutaria el metodo princial
    }//plantilla es una plantilla pero va
    @GetMapping(path="/all")
    public String findAll(Model model){
        List<Persona> personaList = personaService.listarPersona();
        model.addAttribute("Personas",personaList);
        return "add";
    }
//    @PutMapping(path="/edit")
//    public String editPersona(@ModelAttribute Persona persona,Model model){
//        List<Persona> personaList =personaService.listarPersona();
//        model.addAttribute("Personas",personaList);
//        return "addPersona";
//    }

    @DeleteMapping(path="/delete")
    public String eliminarPersona(@RequestParam(name = "Nif") String personaNif, Model model){
        personaService.deleteById(personaNif);
        if (personaService.getById(personaNif)!=null){
            System.out.println("Persona no eliminada con el nif: " + personaNif);
        }
        return "allPersonas";
    }
    @PostMapping(path="/edit")
    public String editarUsuario(@RequestParam(name = "Nif") Model model){
        Persona per = new Persona();
        model.addAttribute("persona",per);
        return "addPersona";//carga la plantilla perosna el nombre de
        //return findALll; te te ejecutaria el metodo princial
    }//plantilla es una plantilla pero va




}
