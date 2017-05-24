/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.PhotoUtilisateur;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.Interfaces.IDAOPhotoUtilisateur;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kods
 */
public class PhotoUtilisateurDAO implements IDAOPhotoUtilisateur{
    
    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PhotoUtilisateurDAO() {
          try {
            conn=MyConnection.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    

    @Override
    public void ajouterPhoto(PhotoUtilisateur photoUtilisateur) {
         String req="INSERT "
             + "INTO "
             + "userPhoto( id_photo,path) values ("
             + "?,?) ";
        try {
            ps=conn.prepareStatement(req);
            ps.setInt(1, photoUtilisateur.getIdPhoto());
            ps.setString(2, photoUtilisateur.getPathPhoto());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updatePhoto(PhotoUtilisateur photoUtilisateur) {
       
    String req="UPDATE userPhoto SET path = ? WHERE id_photo=?";
     try {
            ps=conn.prepareStatement(req);
            ps.setString(1,photoUtilisateur.getPathPhoto());
             ps.setInt(2, photoUtilisateur.getIdPhoto());
            ps.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public void supprimerPhoto(PhotoUtilisateur photoUtilisateur) {
        String req="DELETE from userPhoto WHERE id_photo=?";
     try {
            ps=conn.prepareStatement(req);
            ps.setInt(1, photoUtilisateur.getIdPhoto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public PhotoUtilisateur findPhotoById(int idPhoto) {
        String req="select *  from userPhoto  where id_photo = "+idPhoto;
   
        try {
              Statement st=conn.createStatement();
             
        PhotoUtilisateur photo=new PhotoUtilisateur();     
        rs=st.executeQuery(req);
        while (rs.next()) {
             
   
             photo.setIdPhoto(rs.getInt("id_photo"));
             photo.setPathPhoto(rs.getString("path"));
             
        }
        return photo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    
    }

    @Override
    public PhotoUtilisateur findPhotoByPath(String path) {
        String req="select *  from userPhoto  where path = '"+path+"'";
   
        try {
              Statement st=conn.createStatement();
             
        PhotoUtilisateur photo=new PhotoUtilisateur();     
        rs=st.executeQuery(req);
        while (rs.next()) {
             
   
             photo.setIdPhoto(rs.getInt("id_photo"));
             photo.setPathPhoto(rs.getString("path"));
             
        }
        return photo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    
    }
    
}
