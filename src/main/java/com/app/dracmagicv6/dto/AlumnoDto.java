package com.app.dracmagicv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AlumnoDto {

	public String getNombre();
	
	public String getApellidos();
	
	public String getDireccion();
	
	@JsonIgnore
	public int getEdad();
	
}
