/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro
 */
public class Historial {
    private String codigo; 
    private String fecha;
    private String idpaciente; 
    private String idmedico; 
    private String observaciones;
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdPaciente() {
        return idpaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idpaciente = idPaciente;
    }

    public String getIdMedico() {
        return idmedico;
    }

    public void setIdMedico(String idMedico) {
        this.idmedico = idMedico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Historial() {
    }

    public Historial(String codigo, String fecha, String idpaciente, String idmedico, String observaciones) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.idpaciente = idpaciente;
        this.idmedico = idmedico;
        this.observaciones = observaciones;
    }
    
    @Override
    public String toString(){
        String retornoString = "Codigo: " + this.codigo + ", Fecha: " + this.fecha + ", Id Paciente: " + this.idpaciente 
                + ", Id Medico: " + this.idmedico + ", Observaciones: " + this.observaciones;
        return retornoString;
    }
    
    public static String toList(List<Historial> listadoHistorial){
        String retornoString = "";
        for (Historial item : listadoHistorial) { 
            retornoString += "Codigo: " + item.codigo + ", Fecha: " + item.fecha + ", Id Paciente: " + item.idpaciente 
                + ", Id Medico: " + item.idmedico + ", Observaciones: " + item.observaciones + "\n";
	}
        return retornoString;
    }
    
    public static void Menu(List<Historial> historias, List<Paciente> pacientes, List<Medico> medicos){
        String menu =   "Menu Historia\n" +
                        "1. Registrar Historia.\n" +
                        "2. Mostrar Historias.\n" +
                        "3. Modificar Historia.\n" +
                        "4. Eliminar Historia.\n" +
                        "5. Atras.\n";
        int opcion=0;
        do{
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
                switch (opcion) {
                    case 1:
                        Historial.AgregarHistoria(historias,pacientes,medicos);
                        break;
                    case 2:
                        if(!historias.isEmpty()){
                            JOptionPane.showMessageDialog(null, Historial.toList(historias));
                        } else {
                             JOptionPane.showMessageDialog(null, "No Hay Historias Registradas");
                        }
                        break;
                    case 3:
                        Historial.ModificarHistoria(historias, pacientes, medicos);
                        break;
                    case 4:
                        Historial.EliminarHistoria(historias);
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
    private static int Existe(String id, List<Historial> historias,boolean mensajeExiste){
        Iterator<Historial> iterator = historias.iterator();
        while(iterator.hasNext()){
            Historial historia = iterator.next();
            if(historia.getCodigo().equals(id)){
                if(mensajeExiste)
                    JOptionPane.showMessageDialog(null,"Ya Existe el Codigo, por favor verifique");
                return historias.indexOf(historia);
            }
        }
        return -1;
    }
    private static void AgregarHistoria(List<Historial> historias, List<Paciente> pacientes, List<Medico> medicos) {
        Historial nuevo = new Historial();
        
        String id = "";
        do{
            id = JOptionPane.showInputDialog("Ingrese el nuevo Codigo:");
        }while (Historial.Existe(id,historias,true)!=-1);
        nuevo.setCodigo(id);
        
        String fecha ="";
        do{
            fecha = JOptionPane.showInputDialog("Ingrese la Fecha:\nel formato debe ser dd/MM/aaaa");
            if(!Utils.isDate(fecha))
                    JOptionPane.showMessageDialog(null,"Fecha Invalidad, por favor verifique", "Clinica", JOptionPane.ERROR_MESSAGE);
        } while (!Utils.isDate(fecha));
        nuevo.setFecha(fecha);
        
        JComboBox<Paciente> pacientecomboBox = new JComboBox<>();
        pacientecomboBox.setModel(new DefaultComboBoxModel<>(pacientes.toArray(new Paciente[0])));
        JOptionPane.showMessageDialog(null,pacientecomboBox,"Seleccione un Paciente",JOptionPane.QUESTION_MESSAGE);
        Paciente pacienteSeleccionado = (Paciente) pacientecomboBox.getSelectedItem();
        nuevo.setIdPaciente(pacienteSeleccionado.getId());
        
        JComboBox<Medico> medicocomboBox = new JComboBox<>();
        medicocomboBox.setModel(new DefaultComboBoxModel<>(medicos.toArray(new Medico[0])));
        JOptionPane.showMessageDialog(null,medicocomboBox,"Seleccione un Medico",JOptionPane.QUESTION_MESSAGE);
        Medico medicoSeleccionado = (Medico) medicocomboBox.getSelectedItem();
        nuevo.setIdMedico(medicoSeleccionado.getId());
        
        String observaciones = "";
        do{
            observaciones = JOptionPane.showInputDialog("Ingrese las Observaciones:");
            if(Utils.StringisNullOrEmpty(observaciones))
                    JOptionPane.showMessageDialog(null,"Las Observaciones no pueden estar en blanco, por favor verifique", "Clinica",JOptionPane.ERROR_MESSAGE);
        }while(Utils.StringisNullOrEmpty(observaciones));
        nuevo.setObservaciones(observaciones);
          
        historias.add(nuevo);
    }
    private static void ModificarHistoria(List<Historial> historias, List<Paciente> pacientes, List<Medico> medicos){
        String id = JOptionPane.showInputDialog("Ingrese Id del Historia a modificar:");

        int posicion = Historial.Existe(id, historias, false);
        if (posicion!=-1) {
            Historial modificar = new Historial();
            modificar.setCodigo(id);
            
            String fecha ="";
            do{
                fecha = JOptionPane.showInputDialog("Ingrese la Fecha:\nel formato debe ser dd/MM/aaaa");
                if(!Utils.isDate(fecha))
                    JOptionPane.showMessageDialog(null,"Fecha Invalidad, por favor verifique", "Clinica", JOptionPane.ERROR_MESSAGE);
            } while (!Utils.isDate(fecha));
            modificar.setFecha(fecha);

            JComboBox<Paciente> pacientecomboBox = new JComboBox<>();
            pacientecomboBox.setModel(new DefaultComboBoxModel<>(pacientes.toArray(new Paciente[0])));
            JOptionPane.showMessageDialog(null,pacientecomboBox,"Seleccione un Paciente",JOptionPane.QUESTION_MESSAGE);
            Paciente pacienteSeleccionado = (Paciente) pacientecomboBox.getSelectedItem();
            modificar.setIdPaciente(pacienteSeleccionado.getId());
        
            JComboBox<Medico> medicocomboBox = new JComboBox<>();
            medicocomboBox.setModel(new DefaultComboBoxModel<>(medicos.toArray(new Medico[0])));
            JOptionPane.showMessageDialog(null,medicocomboBox,"Seleccione un Medico",JOptionPane.QUESTION_MESSAGE);
            Medico medicoSeleccionado = (Medico) medicocomboBox.getSelectedItem();
            modificar.setIdMedico(medicoSeleccionado.getId());
        
            String observaciones = "";
            do{
                observaciones = JOptionPane.showInputDialog("Ingrese las Observaciones:");
                if(Utils.StringisNullOrEmpty(observaciones))
                    JOptionPane.showMessageDialog(null,"Las Observaciones no pueden estar en blanco, por favor verifique", "Clinica",JOptionPane.ERROR_MESSAGE);
            }while(Utils.StringisNullOrEmpty(observaciones));
            modificar.setObservaciones(observaciones);
            
            historias.set(posicion,modificar);
        }
        else{
            JOptionPane.showMessageDialog(null,"No Existe la Historia, por favor verifique");
        }
    }
    private static void EliminarHistoria(List<Historial> historias){
        String id = JOptionPane.showInputDialog("Ingrese Codigo de la Historia a Eliminar:");
       
        int posicion = Historial.Existe(id, historias, false);
        if (posicion!=-1) {
            int confirmar = JOptionPane.showConfirmDialog(null, "Confirma la Eliminacion de la Historia", "Clinica", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                historias.remove(posicion);
                JOptionPane.showMessageDialog(null, "Historia Eliminada");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No Existe la Historia, por favor verifique");
        }
    }
}
