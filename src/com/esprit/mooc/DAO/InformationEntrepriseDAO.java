/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.Interfaces.IDAOInformationEntreprise;
import com.esprit.mooc.Util.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kods
 */
public class InformationEntrepriseDAO implements IDAOInformationEntreprise{
    
     private static Connection conn;
    private PreparedStatement ps1;
    private ResultSet rs;

    
    
    public InformationEntrepriseDAO() {
        
        try {
            conn=MyConnection.getInstance();
        } catch (Exception e) {
        }
    }

    @Override
    public void ajouterInforamtionEntreprise(InformationEntreprise infoEntreprise) {
         String req2="INSERT "
                + "INTO "
                + "information_entreprise"
                + "(entreprise_id,specialite,site_web,"
                + "abreviation,attestation,filename,"
                + "description,type,adresse,"
                + "nationnalite,numTel,raison_inscription,"
                + "matriculeFiscal) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
         try {
            ps1=conn.prepareStatement(req2);
            
            ps1.setInt(1, infoEntreprise.getEntrepriseId());
            ps1.setString(2, infoEntreprise.getSpecialite());
            ps1.setString(3, infoEntreprise.getSiteWeb());
            ps1.setString(4, infoEntreprise.getAbreviation());
            ps1.setString(5,infoEntreprise.getAttestation());
            ps1.setString(6, infoEntreprise.getFilename());
            ps1.setString(7, infoEntreprise.getDescription());
            ps1.setString(8,infoEntreprise.getType());
            ps1.setString(9,infoEntreprise.getAdresse());
            ps1.setString(10,infoEntreprise.getNationnalite());
            ps1.setString(11, infoEntreprise.getNumTel());
            ps1.setString(12, infoEntreprise.getRaisonInscription());
            ps1.setString(13, infoEntreprise.getMatriculeFiscal());
            ps1.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  
    }

    @Override
    public void modifierInformationEntreprise(InformationEntreprise infoEntreprise) {
        String req2="update information_entreprise set specialite = ?,"
                + "site_web = ?,"
                + "abreviation = ? ,"
                + "description = ? ,type = ? , adresse = ? ,"
                + "nationnalite = ? ,numTel = ? where id = ?";
         try {
            ps1=conn.prepareStatement(req2); 
             System.out.println("hello from modifier ");
            ps1.setString(1, infoEntreprise.getSpecialite());
            ps1.setString(2, infoEntreprise.getSiteWeb());
            ps1.setString(3, infoEntreprise.getAbreviation());
       
            ps1.setString(4, infoEntreprise.getDescription());
            ps1.setString(5,infoEntreprise.getType());
            ps1.setString(6,infoEntreprise.getAdresse());
            ps1.setString(7,infoEntreprise.getNationnalite());
            ps1.setString(8, infoEntreprise.getNumTel());
            ps1.setInt(9, infoEntreprise.getId());  
            ps1.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(InformationEntrepriseDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void supprimerInformationEntreprise(InformationEntreprise infEntreprise) {
         String req="DELETE FROM information_entreprise where entreprise_id=?";
        try {
            ps1=conn.prepareStatement(req);
            ps1.setInt(1, infEntreprise.getEntrepriseId());
            ps1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     @Override
    public InformationEntreprise findInfoEntrepriseByIdEntreprise(int idEntreprise) {
         Statement st;
        String req="SELECT * FROM information_entreprise WHERE entreprise_id = "+idEntreprise;
         InformationEntreprise informationEntreprise=new InformationEntreprise();
         try {
             st=conn.createStatement();
         
             rs=st.executeQuery(req);
             while (rs.next()) {
               informationEntreprise.setId(rs.getInt("id"));
               informationEntreprise.setEntrepriseId(idEntreprise);
                 System.out.println("from find by "+informationEntreprise.getId());
               informationEntreprise.setAbreviation(rs.getString("abreviation"));
               informationEntreprise.setAdresse(rs.getString("adresse"));
               informationEntreprise.setAttestation(rs.getString("attestation"));
//               informationEntreprise.setDateCreation(rs.getString("date_creation"));
               informationEntreprise.setDescription(rs.getString("description"));
               informationEntreprise.setFilename(rs.getString("filename"));
               informationEntreprise.setMatriculeFiscal(rs.getString("matriculeFiscal"));
               informationEntreprise.setNationnalite(rs.getString("nationnalite"));
               informationEntreprise.setSiteWeb(rs.getString("site_web"));
               informationEntreprise.setSpecialite(rs.getString("specialite"));
               informationEntreprise.setType(rs.getString("type"));
               informationEntreprise.setNumTel(rs.getString("numTel"));
               informationEntreprise.setRaisonInscription(rs.getString("raison_inscription"));
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
    return informationEntreprise;
    }
    
    
    
}
