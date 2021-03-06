package com.app.dracmagicv6.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String apellidos;
	private String direccion;
	private String codigo_postal;
	private String poblacion;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_alta;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_baja;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
	private Date fecha_nacimiento;
	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "alumnos_clases", joinColumns = @JoinColumn(name = "alumno_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "clase_id", referencedColumnName = "id"))
	private List<Clase> clases;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "alumnos_tutors", joinColumns = @JoinColumn(name = "alumno_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tutor_id", referencedColumnName = "id"))
	private List<Tutor> tutors;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "alumnos_fotos", joinColumns = @JoinColumn(name = "alumno_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "foto_id", referencedColumnName = "id"))
	private List<Foto> fotos;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "alumnos_informes", joinColumns = @JoinColumn(name = "alumno_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "informe_id", referencedColumnName = "id"))
	private List<Informe> informes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public Date getFecha_baja() {
		return fecha_baja;
	}
	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Timestamp fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	
	public List<Clase> getClases() {
		return clases;
	}
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	public List<Tutor> getTutors() {
		return tutors;
	}
	public void setTutors(List<Tutor> tutors) {
		this.tutors = tutors;
	}
	public List<Foto> getFotos() {
		return fotos;
	}
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	public List<Informe> getInformes() {
		return informes;
	}
	public void setInformes(List<Informe> informes) {
		this.informes = informes;
	}
	
	
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", codigo_postal=" + codigo_postal + ", poblacion=" + poblacion + ", fecha_alta=" + fecha_alta + ", fecha_baja=" + fecha_baja + ", clases=" + clases
				+ ", tutors=" + tutors + ", fotos=" + fotos + ", informes=" + informes + "]";
	}
}
