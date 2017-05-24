/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Interfaces.IInvitationFormateurEntreprise;
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
 * @author Nesrine Jemai
 */
public class InvitationFormateurEntrepriseDAO implements IInvitationFormateurEntreprise{

    
     private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public InvitationFormateurEntrepriseDAO() {
        try {
            conn=MyConnection.getInstance();
        } catch (Exception e) {
        }
        
    }

    
    @Override
    public void envoyerInvitationFormateurEntreprise(InvitationFormateurEntreprise invitation) {
    
        String req="INSERT INTO invitation_formateur_entreprise (id_formateur,id_entreprise,etat_invitation) VALUES (?,?,?)";
         try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, invitation.getIdFormateur());
             ps.setInt(2, invitation.getIdEntreprise());
             ps.setString(3, "en attente");
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(InvitationFormateurEntrepriseDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    
    }

    @Override
    public void accepterInvitation(int idFormateur,int idEntreprise) {
    
       String req1="UPDATE utilisateur SET id_entreprise_utilisateur=? where id=?";
        try {
            ps=conn.prepareStatement(req1);
            ps.setInt(1, idEntreprise);
             ps.setInt(2, idFormateur);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     @Override
    public void supprimerInvitation(int idFormateur,int idEntreprise) {
    
       String req2="delete from invitation_formateur_entreprise where id_formateur = ? and id_entreprise = ?";
        
        try {
            ps=conn.prepareStatement(req2);
           ps.setInt(1, idFormateur);
           ps.setInt(2, idEntreprise);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void refuserInvitation(int idFormateur,int idEntreprise) {
        
    
        String req="DELETE from invitation_formateur_entreprise where id_formateur=? and id_entreprise=?";
        try {
            ps=conn.prepareStatement(req);
            ps.setInt(1, idFormateur);
            ps.setInt(2, idEntreprise);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InvitationFormateurEntreprise> findInvitationFormateurEntreprise(int idEntreprise) {
       String req=
    "select id_invitation_formateur__entreprise,id_formateur,id_entreprise,etat_invitation from invitation_formateur_entreprise where id_entreprise =?" ;
    
     List<InvitationFormateurEntreprise> l=new ArrayList<>();
      
         try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, idEntreprise);
             rs=ps.executeQuery();
             while(rs.next()){
               InvitationFormateurEntreprise ief=new InvitationFormateurEntreprise();
               ief.setId(rs.getInt("id_invitation_formateur__entreprise"));
               ief.setIdEntreprise(rs.getInt("id_entreprise"));
               ief.setIdFormateur(rs.getInt("id_formateur"));
               ief.setEtat(rs.getString("etat_invitation"));
               l.add(ief);
             }
         } catch (SQLException ex) {
             Logger.getLogger(InvitationFormateurEntrepriseDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    return l;
    } 

    @Override
    public InvitationFormateurEntreprise findInvitationByFormateurIdEntrepriseId(int idFormateur, int idEntreprise) {
      
    String req=
    "select * from invitation_formateur_entreprise where id_entreprise =? and id_formateur=?" ;
    
       InvitationFormateurEntreprise ief=new InvitationFormateurEntreprise();
         try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, idEntreprise);
             ps.setInt(2, idFormateur);
             rs=ps.executeQuery();
             while(rs.next()){
               ief.setId(rs.getInt("id_invitation_formateur__entreprise"));
               ief.setIdEntreprise(rs.getInt("id_entreprise"));
               ief.setIdFormateur(rs.getInt("id_formateur"));
               ief.setEtat(rs.getString("etat_invitation"));
             }
             return ief;
         } catch (SQLException ex) {
             Logger.getLogger(InvitationFormateurEntrepriseDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    return null;
    }

    @Override
    public boolean findInvitationByIdFormateur(int idFormateur) {
         
    String req=
    "select * from invitation_formateur_entreprise where id_formateur=?" ;
    
     try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, idFormateur);
             rs=ps.executeQuery();
             if(rs.next()){
//               ief.setId(rs.getInt("id_invitation_formateur__entreprise"));
//               ief.setIdEntreprise(rs.getInt("id_entreprise"));
//               ief.setIdFormateur(rs.getInt("id_formateur"));
//               ief.setEtat(rs.getString("etat_invitation"));
                return true; 
             } 
         } catch (SQLException ex) {
             Logger.getLogger(InvitationFormateurEntrepriseDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     return false;
    }
    
    
}