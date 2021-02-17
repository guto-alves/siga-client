package com.gutotech.sigaclient.model;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotaConsumer {
	private static String BASE_URL = "http://localhost:8081/notas";

	public List<Nota> findNotasByAvaliacao(long avaliacaoId) {
		String url = BASE_URL + "?avaliacao={avaliacaoId}";

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Nota>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Nota>>() {
				}, avaliacaoId);

		return response.getBody();
	}

	public void salvarNotas(List<Nota> notas, Disciplina disciplina, Avaliacao avaliacao) {
		RestTemplate restTemplate = new RestTemplate();

		Prova prova = new Prova();
		prova.setDisciplina(disciplina.getId());
		prova.setAvaliacao(avaliacao.getId());

		notas.forEach(nota -> {
			Map<String, String> alunoNota = Map.of(
					"ra", nota.getAluno().getRa(), 
					"nota", String.valueOf(nota.getNota()));

			prova.getAlunoNota().add(alunoNota);
		});

		restTemplate.postForObject(BASE_URL, prova, ResponseEntity.class);
	}

	public List<Map<String, String>> getQuadroNotas(long disciplinaId) {
		String url = BASE_URL + "/quadro/" + disciplinaId;

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Map<String, String>>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Map<String, String>>>() {
				});

		return response.getBody();
	}
}
