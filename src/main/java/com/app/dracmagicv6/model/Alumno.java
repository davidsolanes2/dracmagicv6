package com.app.dracmagicv6.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="alumno")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String apellidos;
	private String direccion;
	private String codigo_postal;
	private String poblacion;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha_alta;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha_baja;

	//private tutores_id tutores;
	//private hermano_de hermano_de;
	//private informes_id informes;

	
	/*
	 * @ManyToMany(fetch = FetchType.EAGER) Set<Telefono> likes_1;
	 * 
	 * @JoinTable(name = "Telefonos_likes", joinColumns = @JoinColumn(name =
	 * "idTelefono"), inverseJoinColumns = @JoinColumn(name = "idAlumno"))
	 * 
	 * @ManyToMany(fetch = FetchType.EAGER) Set<Foto> likes_2;
	 * 
	 * @JoinTable(name = "Fotos_likes", joinColumns = @JoinColumn(name = "idFoto"),
	 * inverseJoinColumns = @JoinColumn(name = "idAlumno"))
	 */
	
	@LazyCollection(LazyCollectionOption.FALSE)
	//@ManyToMany(fetch = FetchType.EAGER)
	@ManyToMany
	@JoinTable(name="AlumnoTelefono",
				joinColumns = @JoinColumn(name = "idAlumno"),
				inverseJoinColumns = @JoinColumn(name="idTelefono")
			)
	private List<Telefono> telefonos;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	//@ManyToMany(fetch = FetchType.EAGER)
	@ManyToMany
	@JoinTable(name="AlumnoFotos",
				joinColumns = @JoinColumn(name = "idAlumno"),
				inverseJoinColumns = @JoinColumn(name = "idFoto")
			)
	private List<Foto> fotos;
	
	public void agregarTelefono( Telefono tempTelefono) {
		if (telefonos == null) {
			telefonos = new LinkedList<Telefono>();
		}
		telefonos.add(tempTelefono);
	}
	
	public void agregarFoto( Foto tempFoto) {
		if (fotos == null) {
			fotos = new LinkedList<Foto>();
		}
		fotos.add(tempFoto);
	}
	
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
	public List<Telefono> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
	public List<Foto> getFotos() {
		return fotos;
	}
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", codigo_postal=" + codigo_postal + ", poblacion=" + poblacion + ", fecha_alta=" + fecha_alta
				+ ", fecha_baja=" + fecha_baja + ", telefonos=" + telefonos + ", fotos=" + fotos + "]";
	}
}
