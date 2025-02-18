package es.masanz.UT5.Ejercicio1.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HolamundoController {


    @GetMapping("/saludo")
    public String saludo(@RequestParam(name = "name", required = false, defaultValue = "mundo") String name, Model model) {

        model.addAttribute("name", name);
        return "saludo"; // Renderiza saludo.html con el nombre proporcionado
    }

    @GetMapping("/sumar")//1º
    public String Sumar(
            @RequestParam(name = "num1", required = false, defaultValue = "0") int numero1,
            @RequestParam(name = "num2", required = false, defaultValue = "0") int numero2,
            Model model) {
            int suma = numero1 + numero2;
            model.addAttribute("num1", numero1);
            model.addAttribute("num2", numero2);
            model.addAttribute("resultado", suma);
        return "sumar";//2º esto es lo que va a renderizar la pantalla
    }//deben coincidir los metodosde return debe coincidir con el nombre de la 3ºclase html  de template
     //en sumar tiene que coincidir en



}
