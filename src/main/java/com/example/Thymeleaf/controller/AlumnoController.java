/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Thymeleaf.controller;

import com.example.Thymeleaf.entity.Alumno;
import com.example.Thymeleaf.entity.Carrera;
import com.example.Thymeleaf.repository.AlumnoRepository;
import com.example.Thymeleaf.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LAN CENTER LEONPERU
 */
@Controller
public class AlumnoController {
    
    @Autowired
    private CarreraRepository carreraRepository;
    
    
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @RequestMapping("/prods")
    public String prods(Model model){
        model.addAttribute("prods",alumnoRepository.findAll());
        return "prods";
    }
    
    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("cats",carreraRepository.findAll());
        return "addAlum";
    }
      
     @RequestMapping("/alumadd")
    public String guardar(@RequestParam String nombre, @RequestParam String telefono, @RequestParam String correo, @RequestParam int idcar, Model model) {
        Alumno prod = new Alumno();
        Carrera car = carreraRepository.findById(idcar).get();
        prod.setNombre(nombre);
        prod.setTelefono(telefono);
        prod.setCorreo(correo);
        prod.setCarrera(car);
        alumnoRepository.save(prod);
        return "redirect:/prods";
    }
    
      @RequestMapping("/delalum/{id}")
    public String deleteprod(@PathVariable(value="id") int id) {

        Alumno prod = alumnoRepository.findById(id).orElse(null);
        alumnoRepository.delete(prod);
        return "redirect:/prods";
    }
    
    @RequestMapping("/editalum/{id}")
    public String edit(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("cats", carreraRepository.findAll());
        model.addAttribute("prod", alumnoRepository.findById(id).orElse(null));
        return "editalum";
    }
    
    @RequestMapping("/updatealum")
    public String update(@RequestParam int id, @RequestParam String nombre, @RequestParam String telefono,  @RequestParam String correo, @RequestParam int carrera) {
        Alumno prod = alumnoRepository.findById(id).orElse(null);
        Carrera car = carreraRepository.findById(carrera).get();
        prod.setNombre(nombre);
        prod.setTelefono(telefono);
        prod.setCorreo(correo);
        prod.setCarrera(car);
        alumnoRepository.save(prod);
        return "redirect:/prods";
    }
}
