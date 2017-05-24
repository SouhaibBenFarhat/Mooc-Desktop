/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Test;

import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.InformationEntrepriseDAO;
import com.esprit.mooc.DAO.InformationFormateurDAO;
import com.esprit.mooc.DAO.InvitationEntrepriseFormateurDAO;
import com.esprit.mooc.DAO.PaysDao;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;
import java.util.List;

/**
 *
 * @author kods
 */
public class Test {
     public static String encode(String txt) {
        int compteur = txt.length();
        String ret = "";
        for (int i = 0; i < compteur; i++) {
            if (txt.charAt(i)==' ') {
                ret += "%20";
            } else {
                ret += txt.charAt(i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {

//        
//        Utilisateur user=new Utilisateur();
////        user.setUsename("Ali");
////        user.setUsenameCanonical("Ali");
////        user.setEmail("tryJava@esprit.tn");
////          user.setEmailCanonical("tryJava@esprit.tn");
////        user.setPassword("Ali ");
////        user.setSalt("hello");
//        user.setRole("ROLE_APPRENANT");
//        
//        UtilisateurDAO userDao=new UtilisateurDAO();
////        userDao.ajouterUtilisateur(user);
//        
//        //user.setId(92);
////        user.setEnbaled(0);
////        userDao.modifierUtilisateur(user);
//        userDao.supprimerUtilisateur(92);
//       
//        DisciplineDao disciplineDao=new DisciplineDao();
//        List<String> ls=disciplineDao.findAllDiscipline();
//        for (String l : ls) {
//            System.out.println("hello"+l);  
//        }
//        InvitationEntrepriseFormateurDAO iefdao = new InvitationEntrepriseFormateurDAO();
//        List<InvitationEntrepriseFormateur> lief = iefdao.findInvitationEntrepriseFormateur(19);
//
//        for (InvitationEntrepriseFormateur lief1 : lief) {
//           
//            UtilisateurDAO udao=new UtilisateurDAO();
//             Utilisateur formateur=udao.findFormateurById(lief1.getIdFormateur());
//             
//             
//             InformationFormateurDAO ifdao=new InformationFormateurDAO();
//             InformationFormateur if1=ifdao.findInfoFormateurByIdFormateur(formateur.getId());
//             Utilisateur entreprise=udao.findEntrepriseById(lief1.getIdEntreprise());
//             InformationEntrepriseDAO iEdao=new InformationEntrepriseDAO();
//             InformationEntreprise iE1=iEdao.findInfoEntrepriseByIdEntreprise(entreprise.getId());
//             
//            System.out.println("" + lief1.getIdEntreprise());
//            System.out.println("" + lief1.getIdFormateur());
//            System.out.println("-------"+formateur.toString());
//            System.out.println("-------"+if1.toString());
//            System.out.println("-------"+entreprise);
//            System.out.println("-------"+iE1);

    //    }
//        PaysDao paysDao=new PaysDao();
//        List<String> ls=paysDao.findAll();
//        for (String l : ls) {
//            System.out.println(""+l);
//        }
   
        System.out.println(encode("MahaAkrout"));
    }
    
         
       
}
