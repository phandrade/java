package com.castgroup.assignment3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String obterPaginaWeb() {
		return "index";
	}
}
