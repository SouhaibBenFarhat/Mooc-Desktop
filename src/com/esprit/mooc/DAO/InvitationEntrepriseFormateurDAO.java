/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.mooc.DAO;

import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Interfaces.IInvitationEntrepriseFormateur;
import com.esprit.mooc.Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nesrine Jemai
 */
public class InvitationEntrepriseFormateurDAO implements IInvitationEntrepriseFormateur{

     private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public InvitationEntrepriseFormateurDAO() {
        
        try {
            conn=MyConnection.getInstance();
        } catch (Exception e) {
        }
    }

    
    @Override
    public void envoyerInvitationEntrepriseFormateur(InvitationEntrepriseFormateur invitation) {
        
         String req="INSERT INTO invitation_entreprise_formateur (id_formateur,id_entreprise,etat_invitation) VALUES (?,?,?)";
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
    public void accepterInvitation(int idEntreprise,int idFormateur) {
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
    public void supprimerInvitation(int idEntreprise,int idFormateur) {
         String req="delete from  invitation_entreprise_formateur where  id_formateur = ? and id_entreprise = ?";
        
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
    public void refuserInvitation(InvitationEntrepriseFormateur invitation) {
         String req="DELETE from invitation_entreprise_formateur where	id_invitation_entreprise_formateur =?";
        try {
            ps=conn.prepareStatement(req);
            ps.setInt(1, invitation.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }   }

    @Override
    public List<InvitationEntrepriseFormateur> findInvitationEntrepriseFormateur(int idFormateur) {
    String req=
    "select * from invitation_entreprise_formateur where id_formateur = ?" ;
    
     List<InvitationEntrepriseFormateur> l=new ArrayList<>();
      
         try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, idFormateur);
             rs=ps.executeQuery();
             while(rs.next()){
               InvitationEntrepriseFormateur ief=new InvitationEntrepriseFormateur();
               ief.setId(rs.getInt("id_invitation_entreprise_formateur"));
               ief.setIdEntreprise(rs.getInt("id_entreprise"));
               ief.setIdFormateur(rs.getInt("id_formateur"));
               ief.setEtat(rs.getString("etat_invitation"));
               l.add(ief);
             }
         } catch (SQLException ex) {
             Logger.getLogger(InvitationEntrepriseFormateurDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    return l;
    }
    
     @Override
    public InvitationEntrepriseFormateur findInvitationByFormateurIdEntrepriseId(int idFormateur, int idEntreprise) {
      
    String req=
    "select * from invitation_entreprise_formateur where id_entreprise = ? and id_formateur = ? " ;
    
       InvitationEntrepriseFormateur ief=new InvitationEntrepriseFormateur();
         try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, idEntreprise);
             ps.setInt(2, idFormateur);
             rs=ps.executeQuery();
             while(rs.next()){
               ief.setId(rs.getInt("id_invitation_entreprise_formateur"));
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
    public boolean findInvitByFormateurIdEntrepriseId(int idFormateur, int idEntreprise) {
      
    String req=
    "select * from invitation_entreprise_formateur where id_entreprise = ? and id_formateur = ? " ;
    
       InvitationEntrepriseFormateur ief=new InvitationEntrepriseFormateur();
         try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, idEntreprise);
             ps.setInt(2, idFormateur);
             rs=ps.executeQuery();
             if(rs.next()){
               return true; 
             }
            
         } catch (SQLException ex) {
             Logger.getLogger(InvitationFormateurEntrepriseDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    return false;
    }
    
    
     @Override
    public List<InvitationEntrepriseFormateur> findListInvitationIdEntrepriseId(int idEntreprise) {
      
    String req=
    "select * from invitation_entreprise_formateur where id_entreprise = ? " ;
    
       InvitationEntrepriseFormateur ief=new InvitationEntrepriseFormateur();
       List <InvitationEntrepriseFormateur> l=new ArrayList<>();
         try {
             ps=conn.prepareStatement(req);
             ps.setInt(1, idEntreprise);
             rs=ps.executeQuery();
             while(rs.next()){
               ief.setId(rs.getInt("id_invitation_entreprise_formateur"));
               ief.setIdEntreprise(rs.getInt("id_entreprise"));
               ief.setIdFormateur(rs.getInt("id_formateur"));
               ief.setEtat(rs.getString("etat_invitation"));
               l.add(ief);
             }
             return l;
         } catch (SQLException ex) {
             Logger.getLogger(InvitationFormateurEntrepriseDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    return null;
    }
}


  


    
