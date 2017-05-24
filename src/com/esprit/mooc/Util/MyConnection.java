/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kods
 */
public class MyConnection {
    
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/MOOCV5";
    private String userName="root";
    private String pwd="";
    
    private static Connection conn;
    
    private MyConnection(){
        
    }
    
    private Connection etablirConnexion(){
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,userName,pwd);
              System.out.println("Connexion etablie");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("erreur d'acees à la BD"+e.getMessage());
        }
        return conn;
    }
    
    public static Connection getInstance(){
     
        if(conn==null){
            new MyConnection().etablirConnexion();
        }
        return conn;
    }
}
