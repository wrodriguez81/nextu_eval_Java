/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class Clinica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        List<Paciente> pacientes = new ArrayList<Paciente>();
        
       Paciente paciente = new Paciente();
       paciente.setId("V15418518");
       paciente.setNombre("Alejandro");
       paciente.setApellido("Rodriguez");
       paciente.setEdad(37);
       paciente.setGenero("Masculino");
       pacientes.add(paciente);
       
       paciente = new Paciente();
       paciente.setId("V15418519");
       paciente.setNombre("Willians");
       paciente.setApellido("Rodriguez");
       paciente.setEdad(37);
       paciente.setGenero("Masculino");
        pacientes.add(paciente);
       
       paciente = new Paciente();
       paciente.setId("V15418520");
       paciente.setNombre("Nelson");
       paciente.setApellido("Rodriguez");
       paciente.setEdad(37);
       paciente.setGenero("Masculino");
       pacientes.add(paciente);
        
       System.out.println(paciente.toList(pacientes));

    }
    
}
