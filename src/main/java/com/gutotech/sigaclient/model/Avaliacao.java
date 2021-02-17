package com.gutotech.sigaclient.model;

import java.util.Objects;

public class Avaliacao {
	private Long id;

	private String tipo;

	private double peso;

	public Avaliacao() {
	}

	public Avaliacao(String tipo) {
		this.tipo = tipo;
	}

	public Avaliacao(String tipo, double peso) {
		this.tipo = tipo;
		this.peso = peso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
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
		if (!(obj instanceof Avaliacao)) {
			return false;
		}
		Avaliacao other = (Avaliacao) obj;
		return Objects.equals(id, other.id);
	}
}
