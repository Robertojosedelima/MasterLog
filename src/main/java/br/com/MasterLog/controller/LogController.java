package br.com.MasterLog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/log")
public class LogController {
	
	
	@GetMapping
	public String teste() {
		return "Tested log realized whith sucessfull!";
	}
	
	

}
