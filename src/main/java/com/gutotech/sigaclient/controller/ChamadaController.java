package com.gutotech.sigaclient.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.sigaclient.model.Aluno;
import com.gutotech.sigaclient.model.AlunoConsumer;
import com.gutotech.sigaclient.model.Disciplina;
import com.gutotech.sigaclient.model.DisciplinaConsumer;
import com.gutotech.sigaclient.model.Falta;
import com.gutotech.sigaclient.model.FaltaConsumer;
import com.gutotech.sigaclient.model.FaltasCreationDto;

@Controller
@RequestMapping("chamada/{disciplinaId}")
public class ChamadaController {
	@Autowired
	private FaltaConsumer faltaConsumer;

	@Autowired
	private DisciplinaConsumer disciplinaConsumer;

	@Autowired
	private AlunoConsumer alunoConsumer;

	@ModelAttribute("disciplina")
	public Disciplina findDisciplina(@PathVariable("disciplinaId") long disciplinaId) {
		return disciplinaConsumer.findById(disciplinaId);
	}

	@InitBinder("disciplina")
	public void initDisciplinaBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping
	public String showChamadaForm(Disciplina disciplina, Model model) {
		List<Aluno> alunos = alunoConsumer.findAllByDisciplina(disciplina.getId());

		FaltasCreationDto faltasForm = new FaltasCreationDto();

		Date data = new Date();

		alunos.forEach(aluno -> faltasForm.addFalta(new Falta(aluno, disciplina, data, 0)));

		model.addAttribute("form", faltasForm);
		return "chamada";
	}

	@PostMapping
	public String processFaltaForm(Disciplina disciplina, @ModelAttribute FaltasCreationDto form,
			RedirectAttributes attributes, Model model) {
		faltaConsumer.savarChamada(form.getFaltas(), disciplina.getId(), form.getData());

		attributes.addFlashAttribute("message", "Chamada realizada com sucesso.");
		
		return "redirect:/";
	}

}
