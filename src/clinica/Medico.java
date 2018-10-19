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
public class Medico extends Persona {
    private String especialidad;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Medico() {
    }

    public Medico (String id, String nombre, String apellido, String especialidad) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }
   
    @Override
    public String toString(){
        String retornoString = "Id: " + this.id + ", Nombre: " + this.nombre + ", Apellido: " + this.apellido 
                + ", Especialidad: " + this.especialidad;
        return retornoString;
    }
    
    public static String toList(List<Medico> listadoMedicos){
        String retornoString = "";
        for (Medico item : listadoMedicos) { 
            retornoString += "Id: " + item.id + ", Nombre: " + item.nombre + ", Apellido: " + item.apellido 
                + ", Especialidad: " + item.especialidad + "\n";
	}
        return retornoString;
    }
    
    public static void Menu(List<Medico> medicos){
        String menu =   "Menu Medico\n" +
                        "1. Registrar Medico.\n" +
                        "2. Mostrar Medicos.\n" +
                        "3. Modificar Medico.\n" +
                        "4. Eliminar Medico.\n" +
                        "5. Atras.\n";
        int opcion=0;
        do{
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
                switch (opcion) {
                    case 1:
                        Medico.AgregarMedico(medicos);
                        break;
                    case 2:
                        if(!medicos.isEmpty()){
                            JOptionPane.showMessageDialog(null, Medico.toList(medicos));
                        } else {
                             JOptionPane.showMessageDialog(null, "No Hay Medicos Registrados");
                        }
                        break;
                    case 3:
                        Medico.ModificarMedico(medicos);
                        break;
                    case 4:
                        Medico.EliminarMedico(medicos);
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
    private static int Existe(String id, List<Medico> medicos,boolean mensajeExiste){
        Iterator<Medico> iterator = medicos.iterator();
        while(iterator.hasNext()){
            Medico medico = iterator.next();
            if(medico.getId().equals(id)){
                if(mensajeExiste)
                    JOptionPane.showMessageDialog(null,"Ya Existe el Id, por favor verifique","Clinica",JOptionPane.WARNING_MESSAGE);
                return medicos.indexOf(medico);
            }
        }
        return -1;
    }
    private static void AgregarMedico(List<Medico> medicos) {
        Medico nuevo = new Medico();
        
        String id = "";
        do{
            id = JOptionPane.showInputDialog("Ingrese el nuevo Id:");
        }while (Medico.Existe(id,medicos,true)!=-1);
        nuevo.setId(id);

        nuevo.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre:"));
        nuevo.setApellido(JOptionPane.showInputDialog("Ingrese el Apellido:"));

        String[] especialidad = {"Medicina General","Cirugia","Obstetricia","Pediatria","Radiologia"};
        JComboBox jcb = new JComboBox(especialidad);
        JOptionPane.showMessageDialog(null,jcb,"Seleccione una Especialidad",JOptionPane.QUESTION_MESSAGE);
        nuevo.setEspecialidad(jcb.getSelectedItem().toString());
          
        medicos.add(nuevo);
    }
    private static void ModificarMedico(List<Medico> medicos){
        String id = JOptionPane.showInputDialog("Ingrese Id del Medico a modificar:");

        int posicion = Medico.Existe(id, medicos, false);
        if (posicion!=-1) {
            Medico modificar = new Medico();
            modificar.setId(id);
            modificar.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre:"));
            modificar.setApellido(JOptionPane.showInputDialog("Ingrese el Apellido:"));

            String[] especialidad = {"Medicina General","Cirugia","Obstetricia","Pediatria","Radiologia"};
            JComboBox jcb = new JComboBox(especialidad);
            JOptionPane.showMessageDialog(null,jcb,"Seleccione una Especialidad",JOptionPane.QUESTION_MESSAGE);
            modificar.setEspecialidad(jcb.getSelectedItem().toString());

            medicos.set(posicion,modificar);
        }
        else{
            JOptionPane.showMessageDialog(null,"No Existe el Medico, por favor verifique");
        }
    }
    private static void EliminarMedico(List<Medico> medicos){
        String id = JOptionPane.showInputDialog("Ingrese Id del Medico a Eliminar:");
       
        int posicion = Medico.Existe(id, medicos, false);
        if (posicion!=-1) {
            int confirmar = JOptionPane.showConfirmDialog(null, "Confirma la Eliminacion del Medico", "Clinica", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                medicos.remove(posicion);
                JOptionPane.showMessageDialog(null, "Medico Eliminado");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No Existe el Medico, por favor verifique");
        }
    }
}
