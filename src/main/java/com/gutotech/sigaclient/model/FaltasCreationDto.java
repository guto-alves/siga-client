package com.gutotech.sigaclient.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class FaltasCreationDto {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	private List<Falta> faltas = new ArrayList<>();

	public void addFalta(Falta falta) {
		faltas.add(falta);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Falta> getFaltas() {
		return faltas;
	}

	public void setFaltas(List<Falta> faltas) {
		this.faltas = faltas;
	}

}
