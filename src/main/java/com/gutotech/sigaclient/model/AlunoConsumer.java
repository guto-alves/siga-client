package com.gutotech.sigaclient.model;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlunoConsumer {
	private static String BASE_URL = "http://localhost:8081/alunos";

	public List<Aluno> findAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Aluno>> response = restTemplate.exchange(BASE_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aluno>>() {});
		return response.getBody();
	}

	public List<Aluno> findAllByDisciplina(long id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Aluno>> response = restTemplate.exchange(BASE_URL + "?disciplina={id}", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Aluno>>() {}, id);

		return response.getBody();
	}
}
