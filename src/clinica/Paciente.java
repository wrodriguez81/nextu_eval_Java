/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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

    public Paciente(){}
    
    public Paciente(String id, String nombre, String apellido, int edad, String genero) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
    }
   
    @Override
    public String toString(){
        String retornoString = "Id: " + this.id + ", Nombre: " + this.nombre + ", Apellido: " + this.apellido 
                + ", Edad: " + this.edad + ", Genero: " + this.genero;
        return retornoString;
    }
    
    public static String toList(List<Paciente> listadoPacientes){
        String retornoString = "";
        for (Paciente item : listadoPacientes) { 
            retornoString += "Id: " + item.id + ", Nombre: " + item.nombre + ", Apellido: " + item.apellido 
                + ", Edad: " + item.edad + ", Genero: " + item.genero + "\n";
	}
        return retornoString;
    }
    
    public static void Menu(List<Paciente> pacientes){
        String menu =   "Menu Paciente\n" +
                        "1. Registrar Pacientes.\n" +
                        "2. Mostrar Pacientes.\n" +
                        "3. Modificar Pacientes.\n" +
                        "4. Eliminar Pacientes.\n" +
                        "5. Atras.\n";
        int opcion=0;
        do{
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
                switch (opcion) {
                    case 1:
                        Paciente.AgregarPaciente(pacientes);
                        break;
                    case 2:
                        if(!pacientes.isEmpty()){
                            JOptionPane.showMessageDialog(null, Paciente.toList(pacientes));
                        } else {
                             JOptionPane.showMessageDialog(null, "No Hay Pacientes Registrados");
                        }
                        break;
                    case 3:
                        Paciente.ModificarPaciente(pacientes);
                        break;
                    case 4:
                        Paciente.EliminarPaciente(pacientes);
                        break;    
                    case 5:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion Erronea");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Opcion Erronea");
            }
        }while (opcion!=5);   
    }
    private static int Existe(String id, List<Paciente> pacientes,boolean mensajeExiste){
        Iterator<Paciente> iterator = pacientes.iterator();
        while(iterator.hasNext()){
            Paciente paciente = iterator.next();
            if(paciente.getId().equals(id)){
                if(mensajeExiste)
                    JOptionPane.showMessageDialog(null,"Ya Existe el Id, por favor verifique","Clinica",JOptionPane.WARNING_MESSAGE);
                return pacientes.indexOf(paciente);
            }
        }
        return -1;
    }
    private static void AgregarPaciente(List<Paciente> pacientes) {
        Paciente nuevo = new Paciente();
        
        String id = "";
        do{
            id = JOptionPane.showInputDialog("Ingrese el nuevo Id:");
        }while (Paciente.Existe(id,pacientes,true)!=-1);
        nuevo.setId(id);

        nuevo.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre:"));
        nuevo.setApellido(JOptionPane.showInputDialog("Ingrese el Apellido:"));

        String edad ="";
        do{
            edad = JOptionPane.showInputDialog("Ingrese la Edad:");
            if(!Utils.isInteger(edad))
                JOptionPane.showMessageDialog(null,"Edad Invalida, por favor verifique","Clinica",JOptionPane.WARNING_MESSAGE);
        } while (!Utils.isInteger(edad));
        nuevo.setEdad(Integer.parseInt(edad));

        String[] genero = {"Masculino","Femenino"};
        JComboBox jcb = new JComboBox(genero);
        JOptionPane.showMessageDialog(null,jcb,"Seleccione un Genero",JOptionPane.QUESTION_MESSAGE);
        nuevo.setGenero(jcb.getSelectedItem().toString());
          
        pacientes.add(nuevo);
    }
    private static void ModificarPaciente(List<Paciente> pacientes){
       String id = JOptionPane.showInputDialog("Ingrese Id del Paciente a modificar:");
       
       int posicion = Paciente.Existe(id, pacientes, false);
       if (posicion!=-1) {
        Paciente modificar = new Paciente();
        modificar.setId(id);
        modificar.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre:"));
        modificar.setApellido(JOptionPane.showInputDialog("Ingrese el Apellido:"));
        
        String edad ="";
        do{
            edad = JOptionPane.showInputDialog("Ingrese la Edad:");
            if(!Utils.isInteger(edad))
                JOptionPane.showMessageDialog(null,"Edad Invalida, por favor verifique","Clinica",JOptionPane.WARNING_MESSAGE);
        } while (!Utils.isInteger(edad));
        modificar.setEdad(Integer.parseInt(edad));

        String[] genero = {"Masculino","Femenino"};
        JComboBox jcb = new JComboBox(genero);
        JOptionPane.showMessageDialog(null,jcb,"Seleccione un Genero",JOptionPane.QUESTION_MESSAGE);
        modificar.setGenero(jcb.getSelectedItem().toString());
        
        pacientes.set(posicion,modificar);
       }
       else{
           JOptionPane.showMessageDialog(null,"No Existe el Paciente, por favor verifique");
       }
    }
    private static void EliminarPaciente(List<Paciente> pacientes){
       String id = JOptionPane.showInputDialog("Ingrese Id del Paciente a Eliminar:");
       
       int posicion = Paciente.Existe(id, pacientes, false);
       if (posicion!=-1) {
        int confirmar = JOptionPane.showConfirmDialog(null, "Confirma la Eliminacion del Paciente", "Clinica", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            pacientes.remove(posicion);
            JOptionPane.showMessageDialog(null, "Paciente Eliminado");
        }
       }else{
           JOptionPane.showMessageDialog(null,"No Existe el Paciente, por favor verifique");
       }
    }
 }
