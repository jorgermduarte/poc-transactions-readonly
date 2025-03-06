package pt.jorgeduarte.demo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

     @GetMapping("/")
     public String getHome(){
          return "hello world";
     }
}
