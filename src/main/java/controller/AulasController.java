package controller;

import model.*;
import view.VentanaAulas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class AulasController {

    private VentanaAulas view;
    private AulasService aulasService;
    private EquiposService equiposService;


    public AulasController() {
        this.view = new VentanaAulas();
        aulasService = new AulasService();
        equiposService = new EquiposService();
    }

    public void iniciar(){
        TablasSQL.createTable();
        cargarComboBox();
        actualizarTabla();
        configurarBotones();
        view.setVisible(true);
    }

    private void configurarBotones() {
        view.comboAulas.addActionListener(e -> {detallesAula(); actualizarTabla();});
        view.btnAgregar.addActionListener(e -> accionAnadirEquipo());
        view.btnModificarRam.addActionListener(e -> accionModificarRam());
        view.btnClausurarAula.addActionListener(e -> clausurarAula());
        view.btnNuevaAula.addActionListener(e -> accionNuevaAula());
    }

    private void cargarComboBox(){
        System.out.println("Rellenando ComboBox");

        List<Aulas> lista = aulasService.obtenerAulas();

        System.out.println("Lista de aulas: "+lista.size());

        view.comboAulas.removeAllItems();
        for (Aulas a : lista){
            view.comboAulas.addItem(a);
        }
        System.out.println("Lista de aulas añadida exitosamente");
    }

    public void actualizarTabla() {
        Aulas aulaSeleccionada = (Aulas) view.comboAulas.getSelectedItem();

        view.modelListaEquipos.clear();

        if(aulaSeleccionada != null){
            List<Equipos> lista = EquiposService.obtenerEquipos(aulaSeleccionada.getCod_aula());

            for(Equipos equipo : lista){
                view.modelListaEquipos.addElement(equipo);
            }
        }
    }

    public void detallesAula() {
        Aulas aulaSeleccionada = (Aulas) view.comboAulas.getSelectedItem();
        if (aulaSeleccionada == null) {
            view.txtCodAula.setText("");
            view.txtNombreAula.setText("");
            view.txtUbicacion.setText("");
            view.txtCapacidad.setText("");
            return;
        }
        String codigo = aulaSeleccionada.getCod_aula();
        String nombre = aulaSeleccionada.getNombre_aula();
        String ubicacion = aulaSeleccionada.getUbicacion();
        String capacidad = String.valueOf(aulaSeleccionada.getCapacidad_Alumnos());

        view.txtCodAula.setText(codigo);
        view.txtNombreAula.setText(nombre);
        view.txtUbicacion.setText(ubicacion);
        view.txtCapacidad.setText(capacidad);
    }

    // Reglas de negocio y botones
    private void accionAnadirEquipo() {
        Aulas aula = (Aulas) view.comboAulas.getSelectedItem();

        if (aula == null){
            JOptionPane.showMessageDialog(view, "Seleccione un aula");
            return;
        }

        if(view.txtMarcaModelo.getText().trim().isEmpty() || view.txtRAM.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Campos vacios");
            return;
        }

        try {
            String codEquipo = EquiposService.generarCodigoAleatorio();
            String marcaEquipo = view.txtMarcaModelo.getText();
            String procesador = view.txtProcesador.getText();
            String IP = view.txtIP.getText();
            String OP =  view.txtOP.getText();
            int RAM = Integer.parseInt(view.txtRAM.getText());
            double almacenamiento = Double.parseDouble(view.txtAlmacenamiento.getText());

            Equipos nuevoEquipo = new Equipos(codEquipo , marcaEquipo, procesador, IP, OP, RAM, almacenamiento, aula.getCod_aula());

            String resultado = EquiposService.insertarEquipo(nuevoEquipo);

            if (resultado.equalsIgnoreCase("Update")) {
                JOptionPane.showMessageDialog(view, "Equipo insertado. Código: " + codEquipo);
                actualizarTabla();
                limpiarCampos();
            } else if (resultado.equalsIgnoreCase("limite alcanzado")) {
                JOptionPane.showMessageDialog(view, "Limite de equipos alcanzado");
            } else {
                JOptionPane.showMessageDialog(view, "Error al insertar equipo");
            }


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "La RAM debe de ser un entero");
        }
    }

    private void accionModificarRam() {
        Equipos filaSeleccionada = view.listaEquipos.getSelectedValue();

        if (filaSeleccionada == null){
            JOptionPane.showMessageDialog(view, "Seleccione un equipo");
            return;
        }

        String codEquipo = filaSeleccionada.getCod_equipo();
        int ramActual = filaSeleccionada.getRAM();

        String input = JOptionPane.showInputDialog(view, "Ingrese la nueva RAM del equipo");

        if(input != null && !input.trim().isEmpty()){
            try {
                int ramNueva = Integer.parseInt(input);

                if (EquiposService.modificarRAM(codEquipo, ramNueva)) {
                    JOptionPane.showMessageDialog(view, "Ram modificada");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(view, "Error al modificar RAM");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Introduzca un número entero");
            }
        }
    }

    public void clausurarAula () {
        Aulas aula = (Aulas) view.comboAulas.getSelectedItem();

        if (aula == null){
            JOptionPane.showMessageDialog(view, "Seleccione un aula");
            return;
        }

        String codAula = aula.getCod_aula();

        AulasService.clausurarAula(codAula);
        cargarComboBox();
        actualizarTabla();

        JOptionPane.showMessageDialog(view, "Aula clausurada");

    }

    private void accionNuevaAula() {
        String nombre = JOptionPane.showInputDialog(view, "Ingrese el nombre del aula nueva");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String ubicacion = JOptionPane.showInputDialog(view, "Ingrese el ubicacion del aula "+nombre);
        if (ubicacion == null || ubicacion.trim().isEmpty()) return;

        String capacidadStr = JOptionPane.showInputDialog(view, "Ingrese la capacidad del aula "+nombre);
        if (capacidadStr == null) return;

        try {
            int capacidad = Integer.parseInt(capacidadStr.trim());
            //se genera el codigo char:
            String codigoRandom = AulasService.generarCodigo(nombre);

            Aulas aulaNueva = new Aulas(codigoRandom, nombre, ubicacion, capacidad);

            if(AulasService.insertarAulas(aulaNueva)){
                JOptionPane.showMessageDialog(view, "Aula insertada con el código: "+codigoRandom);
                cargarComboBox();
                view.comboAulas.setSelectedItem(aulaNueva);
            } else {
                JOptionPane.showMessageDialog(view, "Error al insertar Aula");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "La capacidad de aula debe de ser un número entero.");
        }
    }

    private void limpiarCampos() {
        view.txtMarcaModelo.setText("");
        view.txtProcesador.setText("");
        view.txtIP.setText("");
        view.txtOP.setText("");
        view.txtRAM.setText("");
        view.txtAlmacenamiento.setText("");

        //Lo siguiente es para que también resetee el comboBox. Así siempre aparecerá en "Aula1"
       /* if (view.comboAulas.getItemCount() > 0) {
            view.comboAulas.setSelectedIndex(0);
        }

        view.txtMarcaModelo.requestFocus();*/
    }
}
