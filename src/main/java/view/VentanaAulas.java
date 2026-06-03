package view;

import javax.swing.*;
import java.awt.*;

public class VentanaAulas extends JFrame {
    //zona norte
    //public JComboBox<model.Aulas> comboAulas;
    //zona centro izquierda
    public JTextField txtMarcaModelo, txtProcesador, txtIP, txtOP, txtRAM, txtAlmacenamiento;
    // zona norte en conjunto con ComboBox
    public JTextField txtCodAula, txtNombreAula, txtUbicacion, txtCapacidad;
    //zona centro derecha
    //public JList<model.Equipos> listaEquipos;
    //public DefaultListModel<model.Equipos> modelListaEquipos;
    //zona sur (botones)
    public JButton btnAgregar, btnModificarRam, btnClausurarAula, btnNuevaAula;

    public VentanaAulas() {
        setTitle("Gestion de aulas y equipos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Layout principal: el lienzo
        setLayout(new BorderLayout(10,10));

        //zona norte
        JPanel panelNorte = new JPanel(new GridLayout(2,2,10,10));
        panelNorte.setBorder(BorderFactory.createTitledBorder("Selección de Aula"));

        /*
        comboAulas = new JComboBox<>();
        comboAulas.setPreferredSize(new Dimension(200, 30));
        panelNorte.add(comboAulas);
        panelNorte.add(btnNuevaAula = new JButton("Nueva Aula"));

         */

        JPanel panelInformativo = new JPanel(new GridLayout(2,2,5,5));
        panelInformativo.add(new JLabel("Código: "));
        panelInformativo.add(txtCodAula = new JTextField());
        panelInformativo.add(new JLabel("Nombre: "));
        panelInformativo.add(txtNombreAula = new JTextField());
        panelInformativo.add(new JLabel("Ubicación: "));
        panelInformativo.add(txtUbicacion = new JTextField());
        panelInformativo.add(new JLabel("Cap. : "));
        panelInformativo.add(txtCapacidad = new JTextField());

        txtCodAula.setEditable(false);
        txtNombreAula.setEditable(false);
        txtUbicacion.setEditable(false);
        txtCapacidad.setEditable(false);

        panelNorte.add(panelInformativo);
        // Se añade el panel norte de la ventana
        add(panelNorte, BorderLayout.NORTH);


        // zona central
        JPanel panelCentro = new JPanel(new GridLayout(1,2,15,0));
        // zona izquierda
        JPanel panelFormulario = new JPanel(new GridLayout(7,2,5,10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario"));

        txtMarcaModelo = new JTextField();
        txtProcesador = new JTextField();
        txtIP = new JTextField();
        txtOP = new JTextField();
        txtRAM = new JTextField();
        txtAlmacenamiento = new JTextField();

        panelFormulario.add(new JLabel("Marca Modelo:"));
        panelFormulario.add(txtMarcaModelo);
        panelFormulario.add(new JLabel("Procesador:"));
        panelFormulario.add(txtProcesador);
        panelFormulario.add(new JLabel("IP:"));
        panelFormulario.add(txtIP);
        panelFormulario.add(new JLabel("Sistema Operativo:"));
        panelFormulario.add(txtOP);
        panelFormulario.add(new JLabel("RAM:"));
        panelFormulario.add(txtRAM);
        panelFormulario.add(new JLabel("Almacenamiento:"));
        panelFormulario.add(txtAlmacenamiento);

        //Se añade el formulario al panel central
        panelCentro.add(panelFormulario, BorderLayout.EAST);

        //panel derecho
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Lista de Equipos"));

        /*
        modelListaEquipos = new DefaultListModel<>();
        listaEquipos = new JList<>(modelListaEquipos);
        listaEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //meter la lista en ScrollPane
        JScrollPane scrollListaEquipos = new JScrollPane(listaEquipos);
        panelLista.add(scrollListaEquipos, BorderLayout.CENTER);
         */
        //Se añade al panel central
        panelCentro.add(panelLista, BorderLayout.WEST);

        // Añado todo a la ventana como hice con el norte
        add(panelCentro, BorderLayout.CENTER);

        //PANEL SUR
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSur.setBorder(BorderFactory.createEtchedBorder());

        btnAgregar = new JButton("Agregar");
        btnModificarRam = new JButton("Modificar");
        btnClausurarAula = new JButton("Clausurar");

        panelSur.add(btnAgregar);
        panelSur.add(btnModificarRam);
        panelSur.add(btnClausurarAula);
        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

    }

}