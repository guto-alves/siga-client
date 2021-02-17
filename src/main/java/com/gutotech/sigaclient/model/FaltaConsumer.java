package com.gutotech.sigaclient.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FaltaConsumer {
	private static String BASE_URL = "http://localhost:8081/faltas";

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public void savarChamada(List<Falta> faltas, long disciplina, Date data) {
		RestTemplate restTemplate = new RestTemplate();

		Chamada chamada = new Chamada();
		chamada.setDisciplinaId(disciplina);
		chamada.setData(dateFormat.format(data));

		faltas.forEach(falta -> {
			Map<String, String> faltasAluno = Map.of("alunoRa", falta.getAluno().getRa(), "totalFaltas",
					String.valueOf(falta.getTotalFaltas()));
			chamada.getFaltasAlunos().add(faltasAluno);
		});

		restTemplate.postForObject(BASE_URL, chamada, ResponseEntity.class);
	}

	public List<Map<String, String>> getQuadroFaltas(long disciplinaId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = BASE_URL + "/quadro/" + disciplinaId;

		ResponseEntity<List<Map<String, String>>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Map<String, String>>>() {});

		return response.getBody();
	}

}
