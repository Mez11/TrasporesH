package co.persistencia.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//Mapeo Completo %-&
public class Camion implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	//Encapsulamineto PLAY EN OBJECT JAVA OBJECT Pollo 
	//Arriba de cada propiedad ira la anotacion
	
	
	
	private int id;
	//Si ubiera llave compuesta se agrega dos Id
	

	private String matricula;
	
	//private Double  procedencia;
	
	private double modelo;
	
	
	private String tipo;
	
	
	private double potencia;
	//Constructor @.@. para crearlo cn datos 
	//Para la parte de llaves foraneas
	//private List <Camionero> camionero = new ArrayList<>();
	
	public Camion(){
		
	}
	public Camion( String matricula,double moldelo, String equipo , double potencia){
		 super(); //Referencia al  clase padre ya que las clases siempre heredan de Object
	
	
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Double getModelo() {
		return modelo;
	}

	public void setModelo(Double modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public Double getPotencia() {
		return potencia;
	}


	public void setPotencia(Double potencia) {
		this.potencia = potencia;
	}
	
	
	
}
