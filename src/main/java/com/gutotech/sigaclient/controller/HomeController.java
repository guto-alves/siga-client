package com.gutotech.sigaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gutotech.sigaclient.model.DisciplinaConsumer;

@Controller
public class HomeController {

	@Autowired
	private DisciplinaConsumer disciplinaConsumer;

	@GetMapping({ "/", "index", "home" })
	public String showHome(Model model) {
		model.addAttribute("disciplinas", disciplinaConsumer.findAll());
		return "home";
	}
}
