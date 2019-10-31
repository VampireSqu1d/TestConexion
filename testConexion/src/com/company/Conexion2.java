package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion2 {

    //public static void main(String[] args){}

    public static Connection miconexion(){
        String URL = "148.225.60.114";
        String db = "sakila";
        String user = "root";
        String pass = "root";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Nice driver bro");
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex);
        }

        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + db, user , pass);
            System.out.println("Nice connection bro");
            return c;
        } catch(SQLException s){
            System.out.println(s);
        }

        return null;
    }

}
