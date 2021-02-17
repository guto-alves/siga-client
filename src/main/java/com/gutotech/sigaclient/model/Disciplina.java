package com.gutotech.sigaclient.model;

import java.util.Objects;

public class Disciplina {
	private Long id;
	private String nome;
	private String sigla;
	private String turno;
	private int totalAulas;

	public Disciplina() {
	}

	public Disciplina(Long id) {
		this.id = id;
	}

	public Disciplina(String nome, String sigla, String turno, int totalAulas) {
		this.nome = nome;
		this.sigla = sigla;
		this.turno = turno;
		this.totalAulas = totalAulas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getTotalAulas() {
		return totalAulas;
	}

	public void setTotalAulas(int totalAulas) {
		this.totalAulas = totalAulas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Disciplina)) {
			return false;
		}
		Disciplina other = (Disciplina) obj;
		return Objects.equals(id, other.id);
	}
}
