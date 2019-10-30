package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
//reeeeee

public class Ventana3 {

    public static void mostrarTabla(DefaultTableModel modelo, String query) {
        modelo.setRowCount(0);
        //Conexion2 conn = new Conexion2();

        Connection c = Conexion2.miconexion();
        if (c != null) {
            try {
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery("select" + query);
                int index = rs.getMetaData().getColumnCount();
                while(rs.next()) {
                    Object[] tablaSQL = new Object[index];

                    for(int i=0; i< index; i++) {
                        tablaSQL[i] = rs.getObject(i+1);
                    }
                    modelo.addRow(tablaSQL);
                }
                c.close();
            } catch(SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }
        }
    }

    public static void main(String[] args) {

        //Ventana3 v3 = new Ventana3();
        JFrame v = new JFrame("Ventana 3");

        DefaultTableModel modelo = new DefaultTableModel();

        JTable tabla1 = new JTable(modelo);
        JScrollPane scroll1 = new JScrollPane(tabla1);
        JButton boton1 = new JButton("Mostrar");
        JComboBox<String> caja = new JComboBox<>();

        //String[] yeets = {"", "Filmes", "Raiting", "Info", "Num cats"};
        caja.addItem("");
        caja.addItem("Filmes");
        caja.addItem("Raiting");
        caja.addItem("Info");
        caja.addItem("Num cats");

        caja.setBounds(20,20,100,30);
        boton1.setBounds(140,20,100,30);
        tabla1.setBounds(20,55,450,220);
        scroll1.setBounds(20,55,450,220);

        ActionListener listener = e -> {
            if (!(caja.getSelectedIndex() == 0))
                mostrarTabla(modelo, Objects.requireNonNull(caja.getSelectedItem()).toString());
        };

        boton1.addActionListener(listener);

        v.add(caja);
        v.add(boton1);
        v.add(scroll1);

        v.setLayout(null);
        v.setResizable(false);
        v.setSize(500,350);
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
