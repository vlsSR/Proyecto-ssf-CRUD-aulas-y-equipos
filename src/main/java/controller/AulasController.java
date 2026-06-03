package controller;

import model.*;
import view.VentanaAulas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class AulasController {

    private VentanaAulas view;


    public AulasController() {
        this.view = new VentanaAulas();
    }

    public void iniciar() {
        view.setVisible(true);
    }
}