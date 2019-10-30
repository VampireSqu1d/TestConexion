package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.*;
//reeeeee

public class Ventana3 {

    public static void mostrarTabla(DefaultTableModel modeloTabla, String query) {
        modeloTabla.setRowCount(0);
        //Conexion2 conn = new Conexion2();

        Connection c = Conexion2.miconexion();
        if (c != null) {
            try {
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();
                int index = metaData.getColumnCount();
                Object[] idetifiers = new Object[index];

                for (int i = 0; i < index; i++) {
                    idetifiers[i] = metaData.getColumnLabel(i + 1);
                    //System.out.println(metaData.getColumnLabel(i +1));
                }

                modeloTabla.setColumnIdentifiers(idetifiers);

                while(rs.next()) {
                    Object[] tablaSQL = new Object[index];

                    for(int i=0; i< index; i++) {
                        tablaSQL[i] = rs.getObject(i+1);
                        //System.out.println(rs.getObject(i+1));
                    }

                    modeloTabla.addRow(tablaSQL);
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

        DefaultTableModel modeloTabla = new DefaultTableModel();

        JTable tabla1 = new JTable(modeloTabla);
        JScrollPane scroll1 = new JScrollPane(tabla1);
        JButton boton1 = new JButton("Mostrar");
        JComboBox<String> caja = new JComboBox<>();

        caja.addItem("");
        caja.addItem("SELECT * FROM film ORDER BY title;");
        caja.addItem("SELECT rating, count(film_id) as n FROM film GROUP BY rating ORDER BY n;");
        caja.addItem("SELECT f.film_id, f.title, c.name FROM film f, film_category fc, category c WHERE f.film_id = fc.film_id AND fc.category_id = c.category_id ORDER BY title;");
        caja.addItem("SELECT c.name, COUNT(f.film_id) as total FROM film f, film_category fc, category c WHERE f.film_id = fc.film_id AND fc.category_id = c.category_id GROUP BY c.name ORDER BY total;");
        tabla1.setEnabled(false);

        caja.setBounds(20,20,100,30);
        boton1.setBounds(140,20,100,30);
        tabla1.setBounds(20,55,750,280);
        scroll1.setBounds(20,55,750,280);

        ActionListener listener = e -> {
            if (!(caja.getSelectedIndex() == 0)) {
                mostrarTabla(modeloTabla, caja.getSelectedItem().toString());
                caja.setSelectedIndex(0);
            }
        };

        boton1.addActionListener(listener);

        v.add(caja);
        v.add(boton1);
        v.add(scroll1);

        v.setLayout(null);
        //v.setResizable(false);
        v.setSize(800,450);
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
