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
public class Medico extends Persona {
    private String especialidad;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    @Override
    public String toString(){
        String retornoString = "Id: " + this.id + ", Nombre: " + this.nombre + ", Apellido: " + this.apellido 
                + ", Especialidad: " + this.especialidad;
        return retornoString;
    }
    
    public String toList(List<Medico> listadoMedicos){
        String retornoString = "";
        for (Medico item : listadoMedicos) { 
            retornoString += "Id: " + item.id + ", Nombre: " + item.nombre + ", Apellido: " + item.apellido 
                + ", Especialidad: " + item.especialidad + "\n";
	}
        return retornoString;
    }
    
}
