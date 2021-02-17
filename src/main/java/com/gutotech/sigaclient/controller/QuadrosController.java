package com.gutotech.sigaclient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gutotech.sigaclient.model.Avaliacao;
import com.gutotech.sigaclient.model.Disciplina;
import com.gutotech.sigaclient.model.DisciplinaConsumer;
import com.gutotech.sigaclient.model.FaltaConsumer;
import com.gutotech.sigaclient.model.NotaConsumer;

@Controller
public class QuadrosController {
	@Autowired
	private DisciplinaConsumer disciplinaConsumer;

	@Autowired
	private FaltaConsumer faltaConsumer;

	@Autowired
	private NotaConsumer notaConsumer;

	@GetMapping("quadro-faltas/{disciplina}")
	public String showQuadroFaltas(@PathVariable("disciplina") long disciplinaId, Model model) {
		List<Map<String, String>> quadroFaltas = faltaConsumer.getQuadroFaltas(disciplinaId);

		List<String> colunas = new ArrayList<>();

		quadroFaltas.get(0).keySet().forEach((key) -> {
			if (key.contains("ra")) {
				colunas.add("R.A");
			} else if (key.contains("nome")) {
				colunas.add("Nome do Aluno");
			} else if (key.contains("d")) {
				String[] tokens = key.split("_");
				colunas.add(tokens[1] + "/" + tokens[2]);
			} else if (key.contains("total")) {
				colunas.add("Total Faltas");
			} else {
				colunas.add(key);
			}
		});

		Disciplina disciplina = disciplinaConsumer.findById(disciplinaId);

		model.addAttribute("disciplina", disciplina);
		model.addAttribute("colunas", colunas);
		model.addAttribute("quadroFaltas", quadroFaltas);

		return "quadro-faltas";
	}

	@GetMapping("quadro-notas/{disciplina}")
	public String showQuadroNotas(@PathVariable("disciplina") long disciplinaId, Model model) {
		List<Map<String, String>> quadroNotas = notaConsumer.getQuadroNotas(disciplinaId);

		List<String> colunas = new ArrayList<>();
		
		List<Avaliacao> avaliacoes = disciplinaConsumer.findAllAvaliacoes(new Disciplina(disciplinaId));

		quadroNotas.get(0).keySet().forEach((key) -> {
			if (key.contains("ra")) {
				colunas.add("R.A");
			} else if (key.contains("nome")) {
				colunas.add("Nome do Aluno");
			} else if (key.contains("nota")) {
				int avIndex = Integer.parseInt(key.split("_")[1]) - 1;
				colunas.add(avaliacoes.get(avIndex).getTipo());
			} else if (key.contains("media")) {
				colunas.add("Média Final");
			} else if (key.contains("situacao")) {
				colunas.add("Situação");
			} else {
				colunas.add(key);
			}
		});

		Disciplina disciplina = disciplinaConsumer.findById(disciplinaId);

		model.addAttribute("disciplina", disciplina);
		model.addAttribute("colunas", colunas);
		model.addAttribute("quadroNotas", quadroNotas);

		return "quadro-notas";
	}
}
