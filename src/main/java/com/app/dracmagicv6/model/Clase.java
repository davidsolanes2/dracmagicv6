package com.app.dracmagicv6.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="clase")
public class Clase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String nombre_profesor;
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private Date year;
	
	/*
	 * */
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(mappedBy = "clases")
	private List<Alumno> alumnos;
	
	/*
	 * */
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNombre_profesor() {
		return nombre_profesor;
	}
	public void setNombre_profesor(String nombre_profesor) {
		this.nombre_profesor = nombre_profesor;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	@Override
	public String toString() {
		return "Clase [id=" + id + ", name=" + name + ", nombre_profesor=" + nombre_profesor + ", year=" + year
				+ ", user=" + user + ", alumnos=" + alumnos + "]";
	}
	
}
