package com.gutotech.sigaclient.controller;

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
import com.gutotech.sigaclient.model.Avaliacao;
import com.gutotech.sigaclient.model.Disciplina;
import com.gutotech.sigaclient.model.DisciplinaConsumer;
import com.gutotech.sigaclient.model.Nota;
import com.gutotech.sigaclient.model.NotaConsumer;
import com.gutotech.sigaclient.model.NotasCreationDto;

@Controller
@RequestMapping("prova/{disciplinaId}/{avaliacaoId}")
public class LancamentoNotasController {
	@Autowired
	private DisciplinaConsumer disciplinaConsumer;

	@Autowired
	private AlunoConsumer alunoConsumer;

	@Autowired
	private NotaConsumer notaConsumer;

	@ModelAttribute("disciplina")
	public Disciplina findDisciplina(@PathVariable("disciplinaId") long disciplinaId) {
		return disciplinaConsumer.findById(disciplinaId);
	}

	@InitBinder("disciplina")
	public void initDisciplinaBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("avaliacao")
	public Avaliacao findAvaliacao(@PathVariable("avaliacaoId") long avaliacaoId, Disciplina disciplina) {
		return disciplinaConsumer.findAllAvaliacoes(disciplina).stream().filter(av -> av.getId() == avaliacaoId)
				.findFirst().get();
	}

	@InitBinder("avaliacao")
	public void initAvaliacaoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping
	public String initNotaForm(Disciplina disciplina, Avaliacao avaliacao, Model model) {
		boolean creation = false;

		List<Nota> notas = notaConsumer.findNotasByAvaliacao(avaliacao.getId());

		if (notas.size() == 0) {
			List<Aluno> alunos = alunoConsumer.findAllByDisciplina(disciplina.getId());
			alunos.forEach(aluno -> notas.add(new Nota(aluno, 6)));

			creation = true;
		}

		model.addAttribute("disciplina", disciplina);
		model.addAttribute("avaliacao", avaliacao);
		model.addAttribute("form", new NotasCreationDto(notas));
		model.addAttribute("create", creation);

		return "lancamento-notas";
	}

	@PostMapping
	public String salvarNotas(Disciplina disciplina, Avaliacao avaliacao, @ModelAttribute NotasCreationDto notasForm,
			RedirectAttributes attributes, Model model) {
		notaConsumer.salvarNotas(notasForm.getNotas(), disciplina, avaliacao);

		attributes.addFlashAttribute("message", "As notas foram inseridas/atualizadas com sucesso.");

		return "redirect:/";
	}

}
