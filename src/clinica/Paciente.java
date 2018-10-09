/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.util.List;

/**
 *
 * @author Alejandro
 */
public class Paciente  extends Persona{
    private int edad; 
    private String genero;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
   
    @Override
    public String toString(){
        String retornoString = "Id: " + this.id + ", Nombre: " + this.nombre + ", Apellido: " + this.apellido 
                + ", Edad: " + this.edad + ", Genero: " + this.genero;
        return retornoString;
    }
    
    public String toList(List<Paciente> listadoPacientes){
        String retornoString = "";
        for (Paciente item : listadoPacientes) { 
            retornoString += "Id: " + item.id + ", Nombre: " + item.nombre + ", Apellido: " + item.apellido 
                + ", Edad: " + item.edad + ", Genero: " + item.genero + "\n";
	}
        return retornoString;
    }
 }
