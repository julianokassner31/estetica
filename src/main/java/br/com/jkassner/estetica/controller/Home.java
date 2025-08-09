package br.com.jkassner.estetica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home")
public class Home {

    @GetMapping
    public String home() {
        return "Ola Pricila Merlo Kassner";
    }
}
