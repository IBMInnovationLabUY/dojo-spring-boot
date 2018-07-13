package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controlador {
	@RequestMapping("/")
	 @ResponseBody
	 String home() {
	 return "HolaMundo desde spring boot";
	 }
	
	@RequestMapping("/dos")
	 @ResponseBody
	 String dos() {
	 return "HolaMundo desde spring boot - dos";
	 }
}