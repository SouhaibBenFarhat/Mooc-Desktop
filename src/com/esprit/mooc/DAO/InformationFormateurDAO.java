/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Interfaces.IDAOInformationFormateur;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kods
 */
public class InformationFormateurDAO implements IDAOInformationFormateur{

    private static Connection conn;
    private PreparedStatement ps1;
    private ResultSet rs;

    
    
    public InformationFormateurDAO() {
        
        try {
            conn=MyConnection.getInstance();
        } catch (Exception e) {
        }
    }
    @Override
    public void ajouterInformationFormateur(InformationFormateur infFormateur) {
          String req2="INSERT "
                + "INTO "
                + "information_formateur"
                + "(formateur_id,specialite,cv,"
                + "filename,googlePlus,siteWeb,"
                + "aPropos,biographie,miniBiographie)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps1=conn.prepareStatement(req2);
            
            ps1.setInt(1, infFormateur.getIdFormateur());
            ps1.setString(2, infFormateur.getSpecialite());
            ps1.setString(3, infFormateur.getCv());
            ps1.setString(4, infFormateur.getFilename());
            ps1.setString(5,infFormateur.getGooglePlus());
            ps1.setString(6, infFormateur.getSiteWeb());
            ps1.setString(7,infFormateur.getaPropos());
            ps1.setString(8,infFormateur.getBiographie());
            ps1.setString(9,infFormateur.getMiniBiographie());
            
            ps1.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }

    @Override
    public void modifierInformationFormateur(InformationFormateur infFormateur) {
       String req2="update "
                + "information_formateur set "
                + "specialite = ?,googlePlus = ? ,siteWeb = ? ,"
                + "aPropos = ? ,biographie = ?,miniBiographie = ? where formateur_id = ?";
                
        try {
            ps1=conn.prepareStatement(req2);
            
            ps1.setString(1, infFormateur.getSpecialite());

            ps1.setString(2,infFormateur.getGooglePlus());
            ps1.setString(3, infFormateur.getSiteWeb());
            ps1.setString(4,infFormateur.getaPropos());
            ps1.setString(5,infFormateur.getBiographie());
            ps1.setString(6,infFormateur.getMiniBiographie());
            ps1.setInt(7, infFormateur.getIdFormateur());
            ps1.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    
    }

    @Override
    public void supprimerInformationFormateur(InformationFormateur infFormateur) {
        String req="DELETE FROM information_formateur where formateur_id=?";
        try {
            ps1=conn.prepareStatement(req);
            ps1.setInt(1, infFormateur.getIdFormateur());
            ps1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }

    @Override
    public InformationFormateur findInfoFormateurByIdFormateur(int idFormateur) {
       
        String req2="select * from information_formateur where formateur_id="+idFormateur;
        try {
            Statement st=conn.createStatement();
             InformationFormateur infFormateur=new InformationFormateur();
            rs=st.executeQuery(req2);
            while(rs.next()){
               
            infFormateur.setId(rs.getInt("id"));
            infFormateur.setSpecialite(rs.getString("specialite"));
            infFormateur.setCv(rs.getString("cv"));
            infFormateur.setFilename(rs.getString("filename"));
            infFormateur.setGooglePlus(rs.getString("googlePlus"));
            infFormateur.setSiteWeb(rs.getString("siteweb"));
            infFormateur.setaPropos(rs.getString("aPropos"));
            infFormateur.setBiographie(rs.getString("biographie"));
            infFormateur.setMiniBiographie(rs.getString("miniBiographie"));
            infFormateur.setIdFormateur(idFormateur);
            
           return infFormateur;
            }  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
}
