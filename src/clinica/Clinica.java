/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

/**
 *
 * @author Alejandro
 */
public class Clinica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Paciente paciente = new Paciente();
       paciente.setId("V15418518");
       paciente.setNombre("Alejandro");
       paciente.setApellido("Rodriguez");
       paciente.setEdad(37);
       paciente.setGenero("Masculino");
       
       System.out.println(paciente.getGenero());
    }
    
}
