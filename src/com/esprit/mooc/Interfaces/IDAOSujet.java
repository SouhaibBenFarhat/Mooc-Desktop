/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import java.util.List;

/**
 *
 * @author souhaib
 */
public interface IDAOSujet {

    public boolean ajouterSujet(Sujet sujet);

    public Boolean modifierSujet(Sujet sujet);

    public Boolean supprimerSujet(int id);

    public List<Sujet> findAllSujets(int idForum);

    public List<Sujet> findSujetByUtilisateur(int idUtilisateur);

    public List<Sujet> chercherSujet(String titreSujet, int idForum);

}
