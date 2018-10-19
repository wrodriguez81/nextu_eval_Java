/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro
 */
public class Clinica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        List<Paciente> pacientes = new ArrayList<>();
        List<Medico> medicos = new ArrayList<>();
        List<Historial> historias = new ArrayList<>();
        
        pacientes.add(new Paciente("V001","Sebastian","Rodriguez",8,"Masculino"));
        pacientes.add(new Paciente("V002","Nelson","Rodriguez",12,"Masculino"));
        pacientes.add(new Paciente("V003","Alejandro","Rodriguez",21,"Masculino"));
        pacientes.add(new Paciente("V004","Willians","Rodriguez",37,"Masculino"));
        
        medicos.add(new Medico("M001","Dr. Sebastian","Rodriguez","Medicina General"));
        medicos.add(new Medico("M002","Dr. Nelson","Rodriguez","Cirugia"));
        medicos.add(new Medico("M003","Dr. Alejandro","Rodriguez","Obstetricia"));
        medicos.add(new Medico("M004","Dr. Willians","Rodriguez","Pediatria"));

        String menuPrincipal="Menu Principal\n"
                            +   "1. Gestionar Pacientes.\n" +
                                "2. Gestionar Médicos.\n" +
                                "3. Gestionar Historial Clínico.\n" +
                                "4. Salir";
        int opcion=0;
        do{
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menuPrincipal));
                switch (opcion) {
                    case 1:
                        Paciente.Menu(pacientes);
                        break;
                    case 2:
                        Medico.Menu(medicos);
                        break;
                    case 3:
                        Historial.Menu(historias, pacientes, medicos);
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Fin del Programa");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion Erronea");
                }  
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Opcion Erronea");
            }
        }while (opcion!=4);
    }
}
