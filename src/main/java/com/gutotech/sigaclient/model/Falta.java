package com.gutotech.sigaclient.model;

import java.util.Date;

public class Falta {
	private Aluno aluno;

	private Disciplina disciplina;

	private Date data;

	private int totalFaltas;

	public Falta() {
	}

	public Falta(Aluno aluno, Disciplina disciplina, Date data, int totalFaltas) {
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.data = data;
		this.totalFaltas = totalFaltas;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(int totalFaltas) {
		this.totalFaltas = totalFaltas;
	}

	@Override
	public String toString() {
		return "Falta [aluno=" + aluno.getNome() + ", data=" + data
				+ ", totalFaltas=" + totalFaltas + "]";
	}
}
