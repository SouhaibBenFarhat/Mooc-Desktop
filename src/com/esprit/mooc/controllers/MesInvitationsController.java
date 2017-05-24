/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.InformationEntrepriseDAO;
import com.esprit.mooc.DAO.InformationFormateurDAO;
import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import com.esprit.mooc.DAO.InvitationEntrepriseFormateurDAO;
import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author kods
 */
class MesInvitationsController implements Initializable{
    
    

    Utilisateur loggedUser;
    MesInvitationsController(Utilisateur loggedUser) {
        this.loggedUser=loggedUser;
        
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       if(loggedUser.getRole().contains("FORMATEUR"))
       {
          
           if(loggedUser.getIdEntrepriseUtilisateur()==0){
        InvitationEntrepriseFormateurDAO iefdao = new InvitationEntrepriseFormateurDAO();
        List<InvitationEntrepriseFormateur> lief = iefdao.findInvitationEntrepriseFormateur(loggedUser.getId());

        for (InvitationEntrepriseFormateur lief1 : lief) {
           
            UtilisateurDAO udao=new UtilisateurDAO();
             Utilisateur formateur=udao.findFormateurById(lief1.getIdFormateur());
             InformationFormateurDAO ifdao=new InformationFormateurDAO();
             InformationFormateur if1=ifdao.findInfoFormateurByIdFormateur(formateur.getId());
             Utilisateur entreprise=udao.findEntrepriseById(lief1.getIdEntreprise());
             InformationEntrepriseDAO iEdao=new InformationEntrepriseDAO();
             InformationEntreprise iE1=iEdao.findInfoEntrepriseByIdEntreprise(entreprise.getId());
            System.out.println("Invitation de entreprise vers formateur");
            System.out.println("" + lief1.getIdEntreprise());
            System.out.println("" + lief1.getIdFormateur());
            System.out.println("-------"+formateur.toString());
            System.out.println("-------"+if1.toString());
            System.out.println("-------"+entreprise);
            System.out.println("-------"+iE1);

        }
           } 
           
       }
       else 
           if(loggedUser.getRole().contains("ENTREPRISE")){
               
                InvitationFormateurEntrepriseDAO ifedao = new InvitationFormateurEntrepriseDAO();
        List<InvitationFormateurEntreprise> life = ifedao.findInvitationFormateurEntreprise(loggedUser.getId());

        for (InvitationFormateurEntreprise life1 : life) {
           
            UtilisateurDAO udao=new UtilisateurDAO();
             Utilisateur formateur=udao.findFormateurById(life1.getIdFormateur());
             InformationFormateurDAO ifdao=new InformationFormateurDAO();
             InformationFormateur if1=ifdao.findInfoFormateurByIdFormateur(formateur.getId());
             Utilisateur entreprise=udao.findEntrepriseById(life1.getIdEntreprise());
             InformationEntrepriseDAO iEdao=new InformationEntrepriseDAO();
             InformationEntreprise iE1=iEdao.findInfoEntrepriseByIdEntreprise(entreprise.getId());
             System.out.println("Invitation du formateur vers entreprise"); 
            System.out.println("" + life1.getIdEntreprise());
            System.out.println("" + life1.getIdFormateur());
            System.out.println("-------"+formateur.toString());
            System.out.println("-------"+if1.toString());
            System.out.println("-------"+entreprise);
            System.out.println("-------"+iE1);

        }
           }
    }
    
}
