package com.castgroup.assignment3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

	@RequestMapping(path="/teste/hello")
	public String helloWorld() {
		return "Ok!";
	}
}
