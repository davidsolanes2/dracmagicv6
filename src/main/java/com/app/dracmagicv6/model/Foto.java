package com.app.dracmagicv6.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "foto")
public class Foto {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		private String descripcion;

		@ManyToMany(mappedBy = "fotos")
		private List<Alumno> alumnos;
		
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public List<Alumno> getAlumnos() {
			return alumnos;
		}

		public void setAlumnos(List<Alumno> alumnos) {
			this.alumnos = alumnos;
		}

		@Override
		public String toString() {
			return "Foto [id=" + id + ", descripcion=" + descripcion + ", alumnos=" + alumnos + "]";
		}		

}
