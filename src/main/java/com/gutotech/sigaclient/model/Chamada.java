package com.gutotech.sigaclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chamada {
	private Long disciplinaId;
	private String data;
	private List<Map<String, String>> faltasAlunos = new ArrayList<>();

	public Chamada() {
	}

	public Long getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Long disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Map<String, String>> getFaltasAlunos() {
		return faltasAlunos;
	}

	public void setFaltasAlunos(List<Map<String, String>> faltasAlunos) {
		this.faltasAlunos = faltasAlunos;
	}

}
