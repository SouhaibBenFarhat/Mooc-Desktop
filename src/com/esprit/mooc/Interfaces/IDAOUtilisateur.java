/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.DAO.InformationEntrepriseDAO;
import com.esprit.mooc.DAO.InformationFormateurDAO;
import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;
import java.util.List;

/**
 *
 * @author kods
 */
public interface IDAOUtilisateur {

    public void ajouterUtilisateur(Utilisateur user);

    public void modifierUtilisateur(Utilisateur user);

    public void supprimerUtilisateur(int id);

    public boolean existeUtilisateur(String username, String mdp);

    public Utilisateur findUtilisateurByLoginMdp(String username, String mdp);

    public Utilisateur findFormateurById(int id);

    public Utilisateur findEntrepriseById(int id);

    public List<Utilisateur> findAllUtilisateur();

    public List<Utilisateur> findUtilisateurByRole(String role);

    public List<Utilisateur> findDisabledUtilisateurByRole(String role);

    public List<Utilisateur> findApprenantByName(String nom);

    public List<Utilisateur> findFormateurByName(String nom);

    public List<Utilisateur> findFormateursByEntreprise(int idEntreprise);

    public List<Utilisateur> findAutreFormateurs(int idEntreprise);

    public List<Utilisateur> findEntrepriseByName(String nom);

    public List<Utilisateur> findMembreComiteByName(String nom);

    public void accepterUtilisateur(Utilisateur user);

    public void disableUtilisateur(int id);

    public void enableUtilisateur(int id);

    public List<Utilisateur> findlockedEntreprises();

    public List<Utilisateur> findDisabledFormateurs();

    public Utilisateur findUtilisateurById(int id);
    
    public void supprimerFormateur(int idFormateur);
    
    public void supprimerEntreprise(int idEntreprise);
}
