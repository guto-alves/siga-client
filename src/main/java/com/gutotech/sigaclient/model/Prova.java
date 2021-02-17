package com.gutotech.sigaclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Prova {
	private long disciplina;
	private long avaliacao;
	private List<Map<String, String>> alunoNota = new ArrayList<>();

	public Prova() {
	}

	public long getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(long disciplina) {
		this.disciplina = disciplina;
	}

	public long getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(long avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Map<String, String>> getAlunoNota() {
		return alunoNota;
	}

	public void setAlunoNota(List<Map<String, String>> alunosNota) {
		this.alunoNota = alunosNota;
	}
}
