/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Interfaces;

import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;

/**
 *
 * @author kods
 */
public interface IDAOInformationFormateur {
    public void ajouterInformationFormateur(InformationFormateur infFormateur);
    public void modifierInformationFormateur(InformationFormateur infFormateur);
    public void supprimerInformationFormateur(InformationFormateur infFormateur);
    public InformationFormateur findInfoFormateurByIdFormateur(int idFormateur);
    
}
