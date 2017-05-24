/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.Pays;
import com.esprit.mooc.Interfaces.IDAOPays;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kods
 */
public class PaysDao implements IDAOPays{

    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PaysDao() {
        try {
            conn=MyConnection.getInstance();
        } catch (Exception e) {
            System.out.println("erreur d'accés paysDAO"+e.getMessage());
        }
    }

    
    @Override
    public List<String> findAll() {
        
        String req="SELECT paysname FROM pays";
        List<String> lsPays=new ArrayList<>();
        try {
            ps=conn.prepareStatement(req);
            rs=ps.executeQuery();
            while (rs.next()) {
                lsPays.add(rs.getString("paysname"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaysDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsPays;
    }
    
}
