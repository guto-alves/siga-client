package com.gutotech.sigaclient.model;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DisciplinaConsumer {
	private static String BASE_URL = "http://localhost:8081/disciplinas";

	public List<Disciplina> findAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Disciplina>> response = restTemplate.exchange(BASE_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Disciplina>>() {});
		return response.getBody();
	}

	public Disciplina findById(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(BASE_URL + "/{id}", Disciplina.class, id);
	}
	
	public List<Avaliacao> findAllAvaliacoes(Disciplina disciplina) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Avaliacao>> response = restTemplate.exchange(BASE_URL + "/{id}/avaliacoes", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Avaliacao>>() {}, disciplina.getId());
		
		return response.getBody();
	}
}
