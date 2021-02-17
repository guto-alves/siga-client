package com.gutotech.sigaclient.model;

import java.util.ArrayList;
import java.util.List;

public class NotasCreationDto {
	private List<Nota> notas = new ArrayList<>();

	public NotasCreationDto(List<Nota> notas) {
		this.notas = notas;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
}
