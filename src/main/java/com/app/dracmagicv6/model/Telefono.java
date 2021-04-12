package com.app.dracmagicv6.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "telefono")
public class Telefono {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String telefono;
	
	@ManyToOne
	@JoinColumn(name="tutor_id")
	private Tutor tutor;
	
	@OneToMany(mappedBy = "telefono")
	private List<Tutor> tutors;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public List<Tutor> getTutors() {
		return tutors;
	}

	public void setTutors(List<Tutor> tutors) {
		this.tutors = tutors;
	}

	@Override
	public String toString() {
		return "Telefono [id=" + id + ", telefono=" + telefono + ", tutor=" + tutor + ", tutors=" + tutors + "]";
	}

	

}
